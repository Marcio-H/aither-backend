package br.com.ifsc.aither.backend.component;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.ifsc.aither.backend.service.UsuarioService;
import io.jsonwebtoken.ExpiredJwtException;
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
			return;
		}

		String username = null;

		try {
			username = extractUsernameFrom(request);
		} catch (ExpiredJwtException e) {
			log.error("Token expirado...");
		} catch (Exception e) {
			log.error("Aconteceu um erro na extração de token de acesso: ", e);
		}

		var usuario = usuarioService.loadUserByUsername(username);
		var authenticationToken = new UsernamePasswordAuthenticationToken(
				usuario.getUsername(),
				usuario.getPassword(),
				usuario.getAuthorities()
		);

		log.info("Autorizando com usuário '{}' e permissões '{}'", usuario.getUsername(), usuario.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authenticationToken);
		filterChain.doFilter(request, response);
	}

	private String extractUsernameFrom(HttpServletRequest request) {
		var authorizationHeader = request.getHeader(AUTHORIZATION);

		if (authorizationHeader == null) {
			return "";
		}
		log.info("Extraindo username do token '{}'", authorizationHeader);

		var tokenStr = authorizationHeader.replaceFirst("Bearer ", "");
		var token = tokenFactory.tokenOf(tokenStr);

		return token.getUsername();
	}
}
