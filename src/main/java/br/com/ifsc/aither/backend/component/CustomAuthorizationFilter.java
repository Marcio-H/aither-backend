package br.com.ifsc.aither.backend.component;

import static java.util.Optional.ofNullable;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.ifsc.aither.backend.domain.usuario.UsuarioNull;
import br.com.ifsc.aither.backend.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class CustomAuthorizationFilter extends OncePerRequestFilter {

	private final TokenFactory tokenFactory;

	private final UsuarioService usuarioService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		if(request.getServletPath().equals("/login")) {
			filterChain.doFilter(request, response);
		}
		ofNullable(request.getHeader(HttpHeaders.AUTHORIZATION)).ifPresentOrElse(authorization -> {
			try {
				var tokenStr = authorization.replaceFirst("Bearer ", "");
				var token = tokenFactory.tokenOf(tokenStr);
				var email = token.getEmail();
				var usuario = usuarioService.loadUserByUsername(email);
				var authenticationToken = new UsernamePasswordAuthenticationToken(
						usuario.getUsername(),
						usuario.getPassword(),
						usuario.getAuthorities()
				);

				log.info("Autorizando com usuário '{}' e permissões '{}'", usuario.getUsername(), usuario.getAuthorities());
				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			} catch (Exception e) {
				log.error("Aconteceu um erro na autorização", e);
				setSecurityContextWithUsuarioNull();
			}
		}, this::setSecurityContextWithUsuarioNull);
		filterChain.doFilter(request, response);
	}

	private void setSecurityContextWithUsuarioNull() {
		var usuarioNull = UsuarioNull.builder().build();
		var authenticationToken = new UsernamePasswordAuthenticationToken(
				usuarioNull.getUsername(),
				usuarioNull.getPassword(),
				usuarioNull.getAuthorities()
		);

		log.info("Atribuindo usuário null para esta requisição");
		SecurityContextHolder.getContext().setAuthentication(authenticationToken);
	}
}
