package br.com.ifsc.aither.backend.domain;

import br.com.ifsc.aither.backend.dto.DisciplinaDTO;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import static javax.persistence.FetchType.LAZY;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Disciplina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String descricao;

    @Column
    @Basic(fetch = LAZY, optional = false)
    @NotNull(message = "Imagem é obrigatório")
    private byte[] imagem;

    public static Disciplina of(DisciplinaDTO dto) {
        return Disciplina.builder()
                .id(dto.getId())
                .descricao(dto.getDescricao())
                .imagem(dto.getImagem())
                .build();
    }
}
