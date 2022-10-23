package br.com.ifsc.aither.backend.model;

import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.URL;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.ifsc.aither.backend.autocomplete.ConteudoAutoComplete;
import br.com.ifsc.aither.backend.autocomplete.DisciplinaAutoComplete;
import br.com.ifsc.aither.backend.autocomplete.UsuarioAutoComplete;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(value = Include.NON_NULL) 
public class RedDTO {

    private Integer id;

    @NotBlank(message = "Título é obrigatório")
    private String titulo;

    @NotBlank(message = "Descrição é obrigatório")
    private String descricao;

    private String autor;
    private byte[] imagem;

    @NotBlank(message = "Endereço é obrigatório")
    @URL(message = "Endereço precisa ser uma url")
    private String endereco;

    private UsuarioAutoComplete criadoPor;

    @NotNull(message = "O campo disciplinas não pode ser nulo")
    private Set<DisciplinaAutoComplete> disciplinas;

    @NotEmpty(message = "Obrigatório pelo menos um conteúdo")
    @NotNull(message = "O campo conteúdos não pode ser nulo")
    private Set<ConteudoAutoComplete> conteudos;
}
