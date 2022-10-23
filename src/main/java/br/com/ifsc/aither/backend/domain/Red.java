package br.com.ifsc.aither.backend.domain;

import static javax.persistence.FetchType.LAZY;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.URL;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Red {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    @NotBlank(message = "Título é obrigatório")
    private String titulo;

    @Column
    @NotBlank(message = "Descrição é obrigatório")
    private String descricao;

    @Column
    private String autor;

    @ToString.Exclude
    @Basic(fetch = LAZY)
    private byte[] imagem;

    @Column
    @NotBlank(message = "Endereço é obrigatório")
    @URL
    private String endereco;

    @ManyToOne
    @JoinColumn(name = "criado_por")
    private Usuario criadoPor;

    @ToString.Exclude
    @ManyToMany
    @JoinTable(
            name = "red_disciplina",
            joinColumns = @JoinColumn(name = "red_id"),
            inverseJoinColumns = @JoinColumn(name = "disciplina_id"))
    @NotNull(message = "O campo disciplinas não pode ser nulo")
    private Set<Disciplina> disciplinas = new HashSet<>();

    @ToString.Exclude
    @ManyToMany
    @JoinTable(
            name = "red_conteudo",
            joinColumns = @JoinColumn(name = "red_id"),
            inverseJoinColumns = @JoinColumn(name = "conteudo_id"))
    @NotEmpty(message = "Conteúdos é obrigatório")
    @NotNull(message = "O campo conteúdos não pode ser nulo")
    private Set<Conteudo> conteudos =  new HashSet<>();

    public void setConteudos(Set<Conteudo> conteudos) {
        this.conteudos = conteudos;
        setDisciplinas(this.disciplinas);
    }

    public void setDisciplinas(Set<Disciplina> disciplinas) {
        this.disciplinas = disciplinas.stream()
                .filter(disciplina -> conteudos.stream()
                        .noneMatch(conteudo -> conteudo.possuiDisciplina(disciplina)))
                .collect(Collectors.toSet());
    }

    @Override
    public boolean equals(Object obj) {
        if (super.equals(obj)) {
            return true;
        }
        if (!(obj instanceof Red)) {
            return false;
        }

        Red other = (Red) obj;

        return id != null && id.equals(other.getId());
    }

    @Override
    public int hashCode() {
    	return super.hashCode();
    }
   
}
