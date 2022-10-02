package br.com.ifsc.aither.backend.repository.impl;

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

import br.com.ifsc.aither.backend.autocomplete.DisciplinaAutoComplete;
import br.com.ifsc.aither.backend.autocomplete.impl.DisciplinaAutoCompleteImpl;
import br.com.ifsc.aither.backend.domain.QDisciplina;
import br.com.ifsc.aither.backend.repository.DisciplinaRepositoryCustom;

public class DisciplinaRepositoryCustomImpl implements DisciplinaRepositoryCustom {

	private static final QDisciplina QDOMAIN = QDisciplina.disciplina;
	public static final String FUNCTION_UNACCENT = "FUNCTION('unaccent', {0})";

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public Page<DisciplinaAutoComplete> autoComplete(String query, Pageable pageable) {
		var jpaQueryFactory = new JPAQueryFactory(em);
		var predicate = buildContextFilter(query);
		Class<? extends DisciplinaAutoComplete> queryClass = DisciplinaAutoCompleteImpl.class;
		var bean = Projections.bean(queryClass,
				QDOMAIN.id,
				QDOMAIN.descricao
				);
		var jpaQuery = (JPAQuery<DisciplinaAutoComplete>) jpaQueryFactory
				.select(bean)
				.from(QDOMAIN)
				.where(predicate)
				.orderBy(QDOMAIN.id.asc())
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
		var descricaoExpression = stringTemplate(FUNCTION_UNACCENT, QDOMAIN.descricao);

		predicate.or(descricaoExpression.containsIgnoreCase(queryExpression));
		return predicate;
	}
}
