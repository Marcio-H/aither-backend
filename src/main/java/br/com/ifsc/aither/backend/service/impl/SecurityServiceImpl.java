package br.com.ifsc.aither.backend.service.impl;

import java.util.Objects;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import br.com.ifsc.aither.backend.enums.DominioRecurso;
import br.com.ifsc.aither.backend.service.SecurityService;
import br.com.ifsc.aither.backend.service.UsuarioService;

@Component("securityService")
public class SecurityServiceImpl implements SecurityService {

	@Inject
	private UsuarioService usuarioService;

	@Override
	public boolean hasPermission(String resource, DominioRecurso domain) {
		if (StringUtils.isEmpty(resource)) {
			throw new IllegalArgumentException("Recurso é obrigatório");
		}
		if (Objects.isNull(domain)) {
			throw new IllegalArgumentException("Domínio é obrigatório");
		}

		var username = SecurityContextHolder.getContext().getAuthentication().getName();
		var usuario = usuarioService.loadUserByUsername(username);

		return usuario.possuiAcessoPara(resource, domain);
	}
}
