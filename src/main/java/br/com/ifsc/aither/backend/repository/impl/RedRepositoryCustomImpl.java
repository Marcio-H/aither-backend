package br.com.ifsc.aither.backend.repository.impl;

import static br.com.ifsc.aither.backend.utils.DataBaseConstants.FUNCTION_UNACCENT;
import static com.querydsl.core.types.dsl.Expressions.stringTemplate;
import static org.apache.commons.lang3.StringUtils.isBlank;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import br.com.ifsc.aither.backend.autocomplete.RedAutoComplete;
import br.com.ifsc.aither.backend.autocomplete.impl.RedAutoCompleteImpl;
import br.com.ifsc.aither.backend.domain.QConteudo;
import br.com.ifsc.aither.backend.domain.QDisciplina;
import br.com.ifsc.aither.backend.domain.QRed;
import br.com.ifsc.aither.backend.domain.QUsuario;
import br.com.ifsc.aither.backend.domain.Red;
import br.com.ifsc.aither.backend.repository.RedRepositoryCustom;

public class RedRepositoryCustomImpl implements RedRepositoryCustom {

	private static final QRed RED = QRed.red;
	private static final QConteudo CONTEUDO = QConteudo.conteudo;
	private static final QDisciplina DISCIPLINA = QDisciplina.disciplina;
	private static final QUsuario USUARIO = QUsuario.usuario;

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public Page<RedAutoComplete> autoComplete(String query, Pageable pageable) {
		var jpaQueryFactory = new JPAQueryFactory(em);
		var predicate = buildContextFilter(query);
		Class<? extends RedAutoComplete> queryClass = RedAutoCompleteImpl.class;
		var bean = Projections.bean(queryClass,
				RED.id,
				RED.titulo
				);
		var jpaQuery = (JPAQuery<RedAutoComplete>) jpaQueryFactory
				.selectDistinct(bean)
				.from(RED)
				.leftJoin(RED.disciplinas)
				.leftJoin(RED.conteudos, CONTEUDO)
				.leftJoin(CONTEUDO.disciplinas, DISCIPLINA)
				.leftJoin(RED.criadoPor, USUARIO)
				.where(predicate)
				.orderBy(RED.id.asc())
				.offset(pageable.getOffset())
				.limit(pageable.getPageSize());
		var content = jpaQuery.fetch();

		return new PageImpl<>(content);
	}

	@Override
	public Page<Red> findAll(String query, Pageable pageable) {
		var jpaQueryFactory = new JPAQueryFactory(em);
		var predicate = buildContextFilter(query);
		var jpaQuery = jpaQueryFactory
				.selectDistinct(RED)
				.from(RED)
				.leftJoin(RED.disciplinas)
				.leftJoin(RED.conteudos, CONTEUDO)
				.leftJoin(CONTEUDO.disciplinas, DISCIPLINA)
				.leftJoin(RED.criadoPor, USUARIO)
				.where(predicate)
				.orderBy(RED.id.asc())
				.offset(pageable.getOffset())
				.limit(pageable.getPageSize());
		var content = jpaQuery.fetch();

		return new PageImpl<>(content);
	}

	private BooleanBuilder buildContextFilter(String query) {
		var predicate = new BooleanBuilder();

		if(isBlank(query)) {
			return predicate;
		}

		var queryExpression = stringTemplate(FUNCTION_UNACCENT, query);
		var redTituloExpression = stringTemplate(FUNCTION_UNACCENT, RED.titulo);
		var redAutorExpression = stringTemplate(FUNCTION_UNACCENT, RED.autor);
		var usuarioNameExpression = stringTemplate(FUNCTION_UNACCENT, USUARIO.name);
		var usuarioUsernameExpression = stringTemplate(FUNCTION_UNACCENT, USUARIO.username);
		var disciplinaDescricaoExpression = stringTemplate(FUNCTION_UNACCENT, DISCIPLINA.descricao);
		var conteudoDescricaoExpression = stringTemplate(FUNCTION_UNACCENT, CONTEUDO.descricao);

		predicate.or(redTituloExpression.containsIgnoreCase(queryExpression));
		predicate.or(redAutorExpression.containsIgnoreCase(queryExpression));
		predicate.or(usuarioNameExpression.containsIgnoreCase(queryExpression));
		predicate.or(usuarioUsernameExpression.containsIgnoreCase(queryExpression));
		predicate.or(disciplinaDescricaoExpression.containsIgnoreCase(queryExpression));
		predicate.or(conteudoDescricaoExpression.containsIgnoreCase(queryExpression));
		return predicate;
	}
}
