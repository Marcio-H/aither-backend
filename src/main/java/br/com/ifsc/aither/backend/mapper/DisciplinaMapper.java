package br.com.ifsc.aither.backend.mapper;

import br.com.ifsc.aither.backend.autocomplete.DisciplinaAutoComplete;
import br.com.ifsc.aither.backend.domain.Disciplina;
import br.com.ifsc.aither.backend.model.DisciplinaDTO;

public interface DisciplinaMapper {

	DisciplinaDTO convertDomainToDTO(Disciplina domain);
	Disciplina convertDTOToDomain(DisciplinaDTO dto);
	DisciplinaAutoComplete convertDomainToAutoComplete(Disciplina domain);
}
