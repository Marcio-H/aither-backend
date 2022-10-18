package br.com.ifsc.aither.backend.domain;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

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
public class Conteudo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    @NotBlank(message = "Descrição é obrigatório")
    private String descricao;

    @ToString.Exclude
    @ManyToMany
    @JoinTable(
            name = "conteudo_disciplina",
            joinColumns = @JoinColumn(name = "conteudo_id"),
            inverseJoinColumns = @JoinColumn(name = "disciplina_id"))
    @NotEmpty(message = "O campo disciplinas não pode ser nulo")
    private Set<Disciplina> disciplinas;

    public Boolean possuiDisciplina(Disciplina disciplina) {
        return disciplinas.contains(disciplina);
    }

    @Override
    public boolean equals(Object obj) {
        if (super.equals(obj)) {
            return true;
        }
        if (!(obj instanceof Conteudo)) {
            return false;
        }

        Conteudo other = (Conteudo) obj;
        return id != null && id.equals(other.getId());
    }

    @Override
    public int hashCode() {
    	return super.hashCode();
    }
}
