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

import br.com.ifsc.aither.backend.autocomplete.ConteudoAutoComplete;
import br.com.ifsc.aither.backend.autocomplete.impl.ConteudoAutoCompleteImpl;
import br.com.ifsc.aither.backend.domain.Conteudo;
import br.com.ifsc.aither.backend.domain.QConteudo;
import br.com.ifsc.aither.backend.repository.ConteudoRepositoryCustom;

public class ConteudoRepositoryCustomImpl implements ConteudoRepositoryCustom {

	private static final QConteudo QDOMAIN = QConteudo.conteudo;

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public Page<ConteudoAutoComplete> autoComplete(String query, Pageable pageable) {
		var jpaQueryFactory = new JPAQueryFactory(em);
		var predicate = buildContextFilter(query);
		Class<? extends ConteudoAutoComplete> queryClass = ConteudoAutoCompleteImpl.class;
		var bean = Projections.bean(queryClass,
				QDOMAIN.id,
				QDOMAIN.descricao
				);
		var jpaQuery = (JPAQuery<ConteudoAutoComplete>) jpaQueryFactory
				.select(bean)
				.from(QDOMAIN)
				.where(predicate)
				.orderBy(QDOMAIN.id.asc())
				.offset(pageable.getOffset())
				.limit(pageable.getPageSize());
		var content = jpaQuery.fetch();

		return new PageImpl<>(content);
	}

	@Override
	public Page<Conteudo> findAll(String query, Pageable pageable) {
		var jpaQueryFactory = new JPAQueryFactory(em);
		var predicate = buildContextFilter(query);
		var jpaQuery = jpaQueryFactory
				.selectFrom(QDOMAIN)
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
