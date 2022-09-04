package br.com.ifsc.aither.backend.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import static javax.persistence.FetchType.LAZY;

@ToString
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

    @ToString.Exclude
    @Column
    @Basic(fetch = LAZY, optional = false)
    @NotNull(message = "Imagem é obrigatório")
    private byte[] imagem;

    @Override
    public boolean equals(Object obj) {
        if (super.equals(obj)) {
            return true;
        }
        if (!(obj instanceof Disciplina)) {
            return false;
        }

        Disciplina other = (Disciplina) obj;
        return id != null && id.equals(other.getId());
    }
}
