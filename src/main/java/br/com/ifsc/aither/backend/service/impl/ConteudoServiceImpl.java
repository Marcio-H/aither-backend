package br.com.ifsc.aither.backend.service.impl;

import br.com.ifsc.aither.backend.domain.Conteudo;
import br.com.ifsc.aither.backend.dto.ConteudoDTO;
import br.com.ifsc.aither.backend.repository.ConteudoRepository;
import br.com.ifsc.aither.backend.service.ConteudoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConteudoServiceImpl implements ConteudoService {

	@Autowired
	private ConteudoRepository repository;

	@Override
	public ConteudoDTO create(ConteudoDTO dto) {
		var criado = repository.save(Conteudo.of(dto));

		return ConteudoDTO.of(criado);
	}
}
