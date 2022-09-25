package br.com.ifsc.aither.backend.component;

import static br.com.ifsc.aither.backend.domain.Recurso.recursoNull;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.ifsc.aither.backend.service.RecursoService;
import br.com.ifsc.aither.backend.service.UsuarioService;
import br.com.ifsc.aither.backend.utils.MessageFormatter;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class CustomAuthorizationFilter extends OncePerRequestFilter {

	private final TokenFactory tokenFactory;
	private final UsuarioService usuarioService;
	private final RecursoService recursoService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		if(request.getServletPath().equals("/login")) {
			filterChain.doFilter(request, response);
			return;
		}

		var uri = request.getRequestURI().replaceFirst("/api", "");
		var recurso = recursoService.findByUri(uri).orElse(recursoNull());
		String username = null;

		try {
			username = extractUsernameFrom(request);
		} catch (ExpiredJwtException e) {
			log.error("Token expirado...");
			if (!recurso.isPermiteTodos()) {
				response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token inválido para o recurso que você solicita");
				return;
			}
		} catch (Exception e) {
			log.error("Aconteceu um erro na extração de token de acesso: ", e);
		}

		var usuario = usuarioService.loadUserByUsername(username);

		if (!recurso.isPermiteTodos() && !usuario.possuiAcessoPara(uri)) {
			response.sendError(HttpServletResponse.SC_FORBIDDEN, MessageFormatter.format("Usuario '{}' não possui acesso para o recurso '{}'", usuario.getUsername(), uri));
			return;
		}

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
