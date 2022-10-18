package br.com.ifsc.aither.backend.autocomplete.impl;

import br.com.ifsc.aither.backend.autocomplete.ConteudoAutoComplete;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConteudoAutoCompleteImpl implements ConteudoAutoComplete {

	private Integer id;
	
	private String descricao;

	@Override
	public boolean equals(Object obj) {
		if (super.equals(obj)) {
            return true;
        }
		if (!(obj instanceof ConteudoAutoCompleteImpl)) {
            return false;
        }

		var other = (ConteudoAutoCompleteImpl) obj;

        return id != null && id.equals(other.getId());
	}

	@Override
    public int hashCode() {
    	return super.hashCode();
    }
}
