package br.com.ifsc.aither.backend.domain;

import lombok.*;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;
import java.util.stream.Collectors;

import static javax.persistence.FetchType.LAZY;

@ToString
@Getter
@Setter
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
    private Set<Disciplina> disciplinas = Set.of();

    @ToString.Exclude
    @ManyToMany
    @JoinTable(
            name = "red_conteudo",
            joinColumns = @JoinColumn(name = "red_id"),
            inverseJoinColumns = @JoinColumn(name = "conteudo_id"))
    @NotEmpty(message = "Conteúdos é obrigatório")
    @NotNull(message = "O campo conteúdos não pode ser nulo")
    private Set<Conteudo> conteudos = Set.of();

    @Builder
    private Red(Integer id, String titulo, String descricao, String autor, byte[] imagem, String endereco, Usuario criadoPor, Set<Disciplina> disciplinas, Set<Conteudo> conteudos) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.autor = autor;
        this.imagem = imagem;
        this.endereco = endereco;
        this.criadoPor = criadoPor;
        this.conteudos = conteudos;
        setDisciplinas(disciplinas);
    }

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
}
