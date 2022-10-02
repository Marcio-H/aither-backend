package br.com.ifsc.aither.backend.mapper;

import br.com.ifsc.aither.backend.domain.Disciplina;
import br.com.ifsc.aither.backend.model.DisciplinaDTO;

public interface DisciplinaMapper {

	DisciplinaDTO convertDomainToDto(Disciplina entity);

	Disciplina convertDtoToDomain(DisciplinaDTO dto);
}
