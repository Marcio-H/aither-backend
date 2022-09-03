package br.com.ifsc.aither.backend.domain;

import br.com.ifsc.aither.backend.domain.Conteudo;
import br.com.ifsc.aither.backend.domain.Disciplina;
import br.com.ifsc.aither.backend.domain.usuario.UsuarioEntity;
import br.com.ifsc.aither.backend.dto.RedDTO;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import java.util.Set;
import java.util.stream.Collectors;

import static javax.persistence.FetchType.LAZY;

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
    @NotBlank(message = "Autor é obrigatório")
    private String autor;

    @Basic(fetch = LAZY)
    private byte[] imagem;

    @Column
    @NotBlank(message = "Endereço é obrigatório")
    private String endereco;

    @ManyToOne
    @NotNull(message = "Usuário é obrigatório")
    @JoinColumn(name = "criado_por")
    private UsuarioEntity criadoPor;

    @ManyToMany
    @JoinTable(
            name = "red_disciplina",
            joinColumns = @JoinColumn(name = "red_id"),
            inverseJoinColumns = @JoinColumn(name = "disciplina_id"))
    @NotNull(message = "O campo disciplinas não pode ser nulo")
    private Set<Disciplina> disciplinas;

    @ManyToMany
    @JoinTable(
            name = "red_conteudo",
            joinColumns = @JoinColumn(name = "red_id"),
            inverseJoinColumns = @JoinColumn(name = "conteudo_id"))
    @NotEmpty(message = "Conteúdos é obrigatório")
    @NotNull(message = "O campo conteúdos não pode ser nulo")
    private Set<Conteudo> conteudos;

    @Builder
    private Red(Integer id, String titulo, String descricao, String autor, byte[] imagem, String endereco, UsuarioEntity usuario, Set<Disciplina> disciplinas, Set<Conteudo> conteudos) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.autor = autor;
        this.imagem = imagem;
        this.endereco = endereco;
        this.criadoPor = usuario;
        this.conteudos = conteudos;
        this.disciplinas = disciplinas.stream()
                .filter(disciplina -> conteudos.stream()
                        .anyMatch(conteudo -> conteudo.possuiDisciplina(disciplina)))
                .collect(Collectors.toSet());
    }

    public static Red of(RedDTO dto, UsuarioEntity usuario) {
        var disciplinas = dto.getDisciplinas().stream().map(Disciplina::of).collect(Collectors.toSet());
        var conteudos = dto.getConteudos().stream().map(Conteudo::of).collect(Collectors.toSet());

        return Red.builder()
                .id(dto.getId())
                .titulo(dto.getTitulo())
                .descricao(dto.getDescricao())
                .autor(dto.getAutor())
                .imagem(dto.getImagem())
                .endereco(dto.getEndereco())
                .usuario(usuario)
                .disciplinas(disciplinas)
                .conteudos(conteudos)
                .build();
    }
}
