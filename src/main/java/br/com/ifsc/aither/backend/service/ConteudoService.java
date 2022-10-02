package br.com.ifsc.aither.backend.service;

import java.util.List;

import br.com.ifsc.aither.backend.domain.Conteudo;

public interface ConteudoService {

	Conteudo create(Conteudo conteudo);
	List<Conteudo> listAll();
}
