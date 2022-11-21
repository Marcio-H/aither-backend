package br.com.ifsc.aither.backend.service.impl;

import static br.com.ifsc.aither.backend.utils.DataBaseConstants.FUNCTION_UNACCENT;
import static com.querydsl.core.types.dsl.Expressions.stringTemplate;
import static org.apache.commons.lang3.StringUtils.isBlank;

import javax.inject.Inject;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.BooleanBuilder;

import br.com.ifsc.aither.backend.autocomplete.RedAutoComplete;
import br.com.ifsc.aither.backend.domain.QConteudo;
import br.com.ifsc.aither.backend.domain.QDisciplina;
import br.com.ifsc.aither.backend.domain.QRed;
import br.com.ifsc.aither.backend.domain.QUsuario;
import br.com.ifsc.aither.backend.exceptions.RecordNotFoundException;
import br.com.ifsc.aither.backend.mapper.RedMapper;
import br.com.ifsc.aither.backend.model.RecRedMainPage;
import br.com.ifsc.aither.backend.model.RedDTO;
import br.com.ifsc.aither.backend.repository.RedRepository;
import br.com.ifsc.aither.backend.service.RedService;
import br.com.ifsc.aither.backend.service.UsuarioService;

@Service
public class RedServiceImpl implements RedService {
	
	private static final QRed RED = QRed.red;
	private static final QConteudo CONTEUDO = QConteudo.conteudo;
	private static final QDisciplina DISCIPLINA = QDisciplina.disciplina;
	private static final QUsuario USUARIO = QUsuario.usuario;

	@Inject
	private RedRepository repository;

	@Inject
	private RedMapper mapper;

	@Inject
	private UsuarioService usuarioService;

	@Transactional
	@Override
	public RedDTO create(RedDTO redDTO) {
		var domain = mapper.convertDTOToDomain(redDTO);
		var username = SecurityContextHolder.getContext().getAuthentication().getName();
		var usuario = usuarioService.loadUserByUsername(username);

		domain.setCriadoPor(usuario);

		var created = repository.save(domain);

		return mapper.convertDomainToDTO(created);
	}

	@Transactional
	@Override
	public RedDTO read(Integer id) {
		var domain = repository.findById(id).orElseThrow(() -> new RecordNotFoundException("Red não encontrado"));

		return mapper.convertDomainToDTO(domain);
	}

	@Transactional
	@Override
	public RedDTO update(RedDTO redDTO) {
		var domain = mapper.convertDTOToDomain(redDTO);
		var redSalvo = read(domain.getId());
		var usuarioOriginal = usuarioService.convertAutoCompleteToDomain(redSalvo.getCriadoPor());

		domain.setCriadoPor(usuarioOriginal);

		var updated = repository.save(domain);

		return mapper.convertDomainToDTO(updated);
	}

	@Transactional
	@Override
	public void delete(Integer id) {
		repository.deleteById(id);
	}

	@Transactional
	@Override
	public Page<RedDTO> listAsDTO(String query, Pageable pageable) {
		var page = repository.findAll(buildContextFilter(query), pageable);

		return page.map(mapper::convertDomainToDTO);
	}

	@Transactional
	@Override
	public Page<RedAutoComplete> autoComplete(String query, Pageable pageable) {
		var page = repository.findAll(buildContextFilter(query), pageable);

		return page.map(mapper::convertDomainToAutoComplete);
	}

	private BooleanBuilder buildContextFilter(String query) {
		var predicate = new BooleanBuilder();

		if(isBlank(query)) {
			return predicate;
		}

		var queryExpression = stringTemplate(FUNCTION_UNACCENT, query);
		var redTituloExpression = stringTemplate(FUNCTION_UNACCENT, RED.titulo);
		var redAutorExpression = stringTemplate(FUNCTION_UNACCENT, RED.autor);
		
//		TODO: implementar
//		var usuarioNameExpression = stringTemplate(FUNCTION_UNACCENT, USUARIO.name);
//		var usuarioUsernameExpression = stringTemplate(FUNCTION_UNACCENT, USUARIO.username);
//		var disciplinaDescricaoExpression = stringTemplate(FUNCTION_UNACCENT, DISCIPLINA.descricao);
//		var conteudoDescricaoExpression = stringTemplate(FUNCTION_UNACCENT, CONTEUDO.descricao);

		predicate.or(redTituloExpression.containsIgnoreCase(queryExpression));
		predicate.or(redAutorExpression.containsIgnoreCase(queryExpression));
//		predicate.or(usuarioNameExpression.containsIgnoreCase(queryExpression));
//		predicate.or(usuarioUsernameExpression.containsIgnoreCase(queryExpression));
//		predicate.or(disciplinaDescricaoExpression.containsIgnoreCase(queryExpression));
//		predicate.or(conteudoDescricaoExpression.containsIgnoreCase(queryExpression));
		return predicate;
	}

	@Override
	public Page<RecRedMainPage> mainPage(String query, Pageable pageable) {
		var page = repository.findAll(buildContextFilter(query), pageable);

		return page.map(mapper::convertDomainToMainPage);
	}
}
