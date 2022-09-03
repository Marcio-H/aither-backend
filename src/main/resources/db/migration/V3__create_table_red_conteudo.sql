CREATE TABLE red_conteudo (
    red_id INTEGER,
    conteudo_id INTEGER
);

CREATE TABLE red_disciplina (
    red_id INTEGER,
    disciplina_id INTEGER
);

/* PRIMARY KEYS */
ALTER TABLE red_conteudo ADD CONSTRAINT pk_red_id_and_conteudo_id PRIMARY KEY (red_id, conteudo_id);
ALTER TABLE red_disciplina ADD CONSTRAINT pk_red_id_and_disciplina_id PRIMARY KEY (red_id, disciplina_id);

/* FOREIGN KEYS */
ALTER TABLE red_conteudo ADD CONSTRAINT fk_red_conteudo_red_id FOREIGN KEY (red_id) REFERENCES red(id);
ALTER TABLE red_conteudo ADD CONSTRAINT fk_red_conteudo_conteudo_id FOREIGN KEY (conteudo_id) REFERENCES conteudo(id);
ALTER TABLE red_disciplina ADD CONSTRAINT fk_red_disciplina_red_id FOREIGN KEY (red_id) REFERENCES red(id);
ALTER TABLE red_disciplina ADD CONSTRAINT fk_red_disciplina_disciplina_id FOREIGN KEY (disciplina_id) REFERENCES disciplina(id);
ALTER TABLE conteudo_disciplina ADD CONSTRAINT fk_conteudo_disciplina_conteudo_id FOREIGN KEY (conteudo_id) REFERENCES conteudo(id);
ALTER TABLE conteudo_disciplina ADD CONSTRAINT fk_conteudo_disciplina_disciplina_id FOREIGN KEY (disciplina_id) REFERENCES disciplina(id);

/* INDEXES */
