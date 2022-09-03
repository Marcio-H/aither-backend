package br.com.ifsc.aither.backend.domain;

import br.com.ifsc.aither.backend.dto.ConteudoDTO;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Conteudo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    @NotBlank(message = "Descrição é obrigatório")
    private String descricao;

    @ManyToMany
    @JoinTable(
            name = "conteudo_disciplina",
            joinColumns = @JoinColumn(name = "conteudo_id"),
            inverseJoinColumns = @JoinColumn(name = "disciplina_id"))
    @NotNull(message = "O campo disciplinas não pode ser nulo")
    private Set<Disciplina> disciplinas;

    public static Conteudo of(ConteudoDTO dto) {
        var disciplinas = dto.getDisciplinas().stream()
                .map(Disciplina::of)
                .collect(Collectors.toSet());

        return Conteudo.builder()
                .id(dto.getId())
                .descricao(dto.getDescricao())
                .disciplinas(disciplinas)
                .build();
    }

    public Boolean possuiDisciplina(Disciplina disciplina) {
        return disciplinas.contains(disciplina);
    }
}
