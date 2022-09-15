package db.migration;

import br.com.ifsc.aither.backend.domain.*;
import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;

import java.sql.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static br.com.ifsc.aither.backend.domain.Papel.*;
import static br.com.ifsc.aither.backend.enums.DominioRecurso.ATUALIZAR;
import static br.com.ifsc.aither.backend.enums.DominioRecurso.CRIAR;
import static br.com.ifsc.aither.backend.enums.TipoRecurso.BACKEND;
import static br.com.ifsc.aither.backend.utils.MessageFormatter.format;

public class V3__Initial_insert_data extends BaseJavaMigration {

	private Connection connection;
	private Map<String, String> placeholders;

	@Override
	public void migrate(Context context) throws Exception {
		connection = context.getConnection();
		placeholders = context.getConfiguration().getPlaceholders();

		var recursoConteudoCriar = Recurso.builder()
				.nome("Conteudo")
				.dominio(CRIAR)
				.uri("/conteudo/create")
				.tipo(BACKEND)
				.build();
		var recursoDisciplinaCriar = Recurso.builder()
				.nome("Disciplina")
				.dominio(CRIAR)
				.uri("/disciplina/create")
				.tipo(BACKEND)
				.build();
		var recursoRedCriar = Recurso.builder()
				.nome("Red")
				.dominio(CRIAR)
				.uri("/red/create")
				.tipo(BACKEND)
				.build();
		var recursoUsuarioCriar = Recurso.builder()
				.nome("Usuario")
				.dominio(CRIAR)
				.uri("/usuario/create")
				.permiteTodos(true)
				.tipo(BACKEND)
				.build();
		var recursoUsuarioRefreshToken = Recurso.builder()
				.nome("Usuario")
				.dominio(ATUALIZAR)
				.uri("/usuario/refresh-token")
				.tipo(BACKEND)
				.build();

		recursoConteudoCriar = insert(recursoConteudoCriar);
		recursoDisciplinaCriar = insert(recursoDisciplinaCriar);
		recursoRedCriar = insert(recursoRedCriar);
		recursoUsuarioCriar = insert(recursoUsuarioCriar);
		recursoUsuarioRefreshToken = insert(recursoUsuarioRefreshToken);

		var administrador = Papel.builder()
				.descricao(ADMINISTRADOR)
				.build();
		var professor = Papel.builder()
				.descricao(PROFESSOR)
				.build();
		var estudante = Papel.builder()
				.descricao(ESTUDANTE)
				.build();

		administrador.setAutorizacaos(
				buildAutorizacoes(
					administrador,
					recursoConteudoCriar, recursoDisciplinaCriar, recursoRedCriar,
					recursoUsuarioCriar, recursoUsuarioRefreshToken
				)
		);
		professor.setAutorizacaos(
				buildAutorizacoes(
					professor,
					recursoConteudoCriar, recursoDisciplinaCriar, recursoRedCriar,
					recursoUsuarioCriar, recursoUsuarioRefreshToken
				)
		);
		estudante.setAutorizacaos(
				buildAutorizacoes(
					estudante,
					recursoConteudoCriar, recursoRedCriar, recursoUsuarioCriar,
					recursoUsuarioRefreshToken
				)
		);

		administrador = insert(administrador);
		professor = insert(professor);
		estudante = insert(estudante);

		var usuarioAdministrador = Usuario.builder()
				.username(placeholders.get("usuario.dono.username"))
				.password(placeholders.get("usuario.dono.password"))
				.name(placeholders.get("usuario.dono.name"))
				.papel(administrador)
				.build();

		insert(usuarioAdministrador);
	}

	private Set<Autorizacao> buildAutorizacoes(Papel papel, Recurso... recursos) {
		Set<Autorizacao> autorizacoes = new HashSet<>();

		for (Recurso recurso : recursos) {
			var id = AutorizacaoId.builder()
					.papel(papel)
					.recurso(recurso)
					.build();

			autorizacoes.add(Autorizacao.builder()
					.id(id)
					.restrita(false)
					.build());
		}
		return autorizacoes;
	}

	private Recurso insert(Recurso recurso) throws SQLException {
		var statement = connection.prepareStatement(
				"INSERT INTO recurso(label, nome, dominio, uri, permite_todos, tipo) VALUES (?, ?, ?, ?, ?, ?)",
				Statement.RETURN_GENERATED_KEYS
		);

		statement.setString(1, recurso.getLabel());
		statement.setString(2, recurso.getNome());
		statement.setString(3, recurso.getDominio().toString());
		statement.setString(4, recurso.getUri());
		statement.setBoolean(5, recurso.isPermiteTodos());
		statement.setString(6, recurso.getTipo().toString());

		int linhasAfetadas = statement.executeUpdate();

		if (linhasAfetadas == 0) {
			throw new SQLException(format("Não foi possível criar o recurso '{}'", recurso));
		}
		try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
			if (generatedKeys.next()) {
				recurso.setId(generatedKeys.getInt(1));
			} else {
				throw new SQLException("Criação de recurso falhou, não foi possível recuperar o ID");
			}
		}
		return recurso;
	}

	private Papel insert(Papel papel) throws SQLException {
		var statement = connection.prepareStatement(
				"INSERT INTO papel(descricao) VALUES (?)",
				Statement.RETURN_GENERATED_KEYS
		);

		statement.setString(1, papel.getDescricao());
		execute(statement);
		papel.setId(getGeneratedId(statement));
		insertInBulk(papel.getAutorizacaos());
		return papel;
	}

	private void insertInBulk(Set<Autorizacao> autorizacoes) throws SQLException {
		var maskValues = "(?, ?, ?)";
		String sql = "INSERT INTO autorizacao(papel_id, recurso_id, restrita) VALUES " +
				String.join(", ", Collections.nCopies(autorizacoes.size(), maskValues));
		var statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		var parameterIndex = 1;
		for (Autorizacao autorizacao: autorizacoes) {
			statement.setInt(parameterIndex++, autorizacao.getId().getPapel().getId());
			statement.setInt(parameterIndex++, autorizacao.getId().getRecurso().getId());
			statement.setBoolean(parameterIndex++, autorizacao.isRestrita());
		}
		execute(statement);
	}

	private void insert(Usuario usuario) throws SQLException {
		var statement = connection.prepareStatement(
				"INSERT INTO usuario(username, password, name, papel_id) VALUES (?, ?, ?, ?)",
				Statement.RETURN_GENERATED_KEYS
		);

		statement.setString(1, usuario.getUsername());
		statement.setString(2, usuario.getPassword());
		statement.setString(3, usuario.getName());
		statement.setInt(4, usuario.getPapel().getId());
		execute(statement);
	}

	private void execute(PreparedStatement statement) throws SQLException {
		int linhasAfetadas = statement.executeUpdate();

		if (linhasAfetadas == 0) {
			throw new SQLException(format("Nenhuma linha afetada ao executar: '{}'", statement));
		}
	}

	private Integer getGeneratedId(PreparedStatement statement) throws SQLException {
		try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
			if (generatedKeys.next()) {
				return generatedKeys.getInt(1);
			}
			else {
				throw new SQLException("Criação de recurso falhou, não foi possível recuperar o ID");
			}
		}
	}
}
