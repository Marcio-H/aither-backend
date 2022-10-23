package br.com.ifsc.aither.backend.mapper;

import br.com.ifsc.aither.backend.domain.Conteudo;
import br.com.ifsc.aither.backend.model.ConteudoDTO;

public interface ConteudoMapper {

	ConteudoDTO convertDomainToDTO(Conteudo domain);
	Conteudo convertDTOToDomain(ConteudoDTO dto);
}
