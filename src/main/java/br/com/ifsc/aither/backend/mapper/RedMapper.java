package br.com.ifsc.aither.backend.mapper;

import br.com.ifsc.aither.backend.autocomplete.RedAutoComplete;
import br.com.ifsc.aither.backend.domain.Red;
import br.com.ifsc.aither.backend.model.RecRedMainPage;
import br.com.ifsc.aither.backend.model.RedDTO;

public interface RedMapper {

	RedDTO convertDomainToDTO(Red domain);
	Red convertDTOToDomain(RedDTO dto);
	RedAutoComplete convertDomainToAutoComplete(Red domain);
	RecRedMainPage convertDomainToMainPage(Red domain);
}
