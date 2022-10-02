package br.com.ifsc.aither.backend.service;

import br.com.ifsc.aither.backend.enums.DominioRecurso;

public interface SecurityService {

	boolean hasPermission(String resource, DominioRecurso domain);
}
