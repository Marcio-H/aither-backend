package br.com.ifsc.aither.backend.domain;

import static javax.persistence.FetchType.LAZY;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
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

        var other = (Disciplina) obj;

        return id != null && id.equals(other.getId());
    }

    @Override
    public int hashCode() {
    	return super.hashCode();
    }
}
