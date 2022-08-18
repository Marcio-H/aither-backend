package br.com.ifsc.aither.backend.controller;

import static java.util.Optional.ofNullable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ifsc.aither.backend.component.TokenFactory;
import br.com.ifsc.aither.backend.dto.UsuarioDTO;
import br.com.ifsc.aither.backend.service.UsuarioService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private TokenFactory tokenFactory;

	@PostMapping("/create")
	public UsuarioDTO create(@RequestBody @Valid UsuarioDTO usuario) {
		return usuarioService.create(usuario);
	}

	@GetMapping("/refresh-token")
	public void refreshToken(HttpServletRequest request, HttpServletResponse response) {
		ofNullable(request.getHeader(HttpHeaders.AUTHORIZATION)).ifPresent(authorization -> {
			try {
				var refreshTokenStr = authorization.replaceFirst("Bearer ", "");
				var token = tokenFactory.tokenOf(refreshTokenStr);
				var email = token.getEmail();

				log.info("Gerando novo token de acesso para o usuário '{}'", email);

				var accessToken = tokenFactory.generateToken(email);
				response.setHeader("access_token", accessToken.toString());
			} catch (Exception e) {
				log.error("Aconteceu um erro na autorização", e);
			}
		});
	}
}
