package br.com.ifsc.aither.backend.component;

import static java.util.Optional.ofNullable;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.jsonwebtoken.ExpiredJwtException;
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
			return;
		}

		String email = null;

		try {
			email = extractEmailFrom(request);
		} catch (ExpiredJwtException e) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return;
		} catch (Exception e) {
			log.error("Aconteceu um erro na autorização", e);
		}

		var usuario = usuarioService.loadUserByUsername(email);
		var authenticationToken = new UsernamePasswordAuthenticationToken(
				usuario.getUsername(),
				usuario.getPassword(),
				usuario.getAuthorities()
		);

		log.info("Autorizando com usuário '{}' e permissões '{}'", usuario.getUsername(), usuario.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authenticationToken);
		filterChain.doFilter(request, response);
	}

	private String extractEmailFrom(HttpServletRequest request) {
		var authorizationHeader = request.getHeader(AUTHORIZATION);

		if (authorizationHeader == null) {
			return "";
		}

		var tokenStr = authorizationHeader.replaceFirst("Bearer ", "");
		var token = tokenFactory.tokenOf(tokenStr);

		return token.getEmail();
	}
}