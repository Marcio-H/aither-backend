CREATE TABLE usuario (
    id SERIAL,
    username VARCHAR(125) NOT NULL,
    password VARCHAR(125) NOT NULL,
    name VARCHAR(125) NOT NULL,
    enabled BOOLEAN DEFAULT TRUE,
    papel_id INTEGER NOT NULL
);

CREATE TABLE disciplina (
    id SERIAL,
    descricao VARCHAR(255) NOT NULL,
    imagem BYTEA NOT NULL
);

CREATE TABLE conteudo (
    id SERIAL,
    descricao VARCHAR(255) NOT NULL
);

CREATE TABLE red (
    id SERIAL,
    titulo VARCHAR (255) NOT NULL,
    descricao VARCHAR(1024) NOT NULL,
    autor VARCHAR(255),
    imagem BYTEA,
    endereco VARCHAR(255) NOT NULL,
    criado_por INTEGER NOT NULL
);

CREATE TABLE conteudo_disciplina (
    conteudo_id INTEGER NOT NULL,
    disciplina_id INTEGER NOT NULL
);

CREATE TABLE red_conteudo (
    red_id INTEGER NOT NULL,
    conteudo_id INTEGER NOT NULL
);

CREATE TABLE red_disciplina (
    red_id INTEGER NOT NULL,
    disciplina_id INTEGER NOT NULL
);

CREATE TABLE papel (
    id SERIAL,
    descricao VARCHAR(255) NOT NULL
);

CREATE TABLE recurso (
    id SERIAL,
    label VARCHAR(255),
    nome VARCHAR(255) NOT NULL,
    dominio VARCHAR(9) NOT NULL,
    urn VARCHAR(255) NOT NULL,
    permite_todos BOOLEAN NOT NULL DEFAULT FALSE,
    tipo VARCHAR(8) NOT NULL
);

CREATE TABLE autorizacao(
    papel_id INTEGER NOT NULL,
    recurso_id INTEGER NOT NULL,
    restrita BOOLEAN DEFAULT FALSE
);

/* PRIMARY KEYS */
ALTER TABLE usuario ADD CONSTRAINT pk_usuario_id PRIMARY KEY (id);
ALTER TABLE disciplina ADD CONSTRAINT pk_disciplina_id PRIMARY KEY (id);
ALTER TABLE conteudo ADD CONSTRAINT pk_conteudo_id PRIMARY KEY (id);
ALTER TABLE red ADD CONSTRAINT pk_red_id PRIMARY KEY (id);
ALTER TABLE conteudo_disciplina ADD CONSTRAINT pk_conteudo_disciplina_conteudo_id_and_disciplina_id PRIMARY KEY (conteudo_id, disciplina_id);
ALTER TABLE red_conteudo ADD CONSTRAINT pk_red_id_and_conteudo_id PRIMARY KEY (red_id, conteudo_id);
ALTER TABLE red_disciplina ADD CONSTRAINT pk_red_id_and_disciplina_id PRIMARY KEY (red_id, disciplina_id);
ALTER TABLE papel ADD CONSTRAINT pk_papel_id PRIMARY KEY (id);
ALTER TABLE recurso ADD CONSTRAINT pk_recurso_backend_id PRIMARY KEY (id);
ALTER TABLE autorizacao ADD CONSTRAINT pk_autorizacao_papel_id_and_recurso_id_and_tipo_recurso PRIMARY KEY (papel_id, recurso_id);

/* FOREIGN KEYS */
ALTER TABLE red ADD CONSTRAINT fk_red_criado_por FOREIGN KEY (criado_por) REFERENCES usuario(id);
ALTER TABLE red_conteudo ADD CONSTRAINT fk_red_conteudo_red_id FOREIGN KEY (red_id) REFERENCES red(id);
ALTER TABLE red_conteudo ADD CONSTRAINT fk_red_conteudo_conteudo_id FOREIGN KEY (conteudo_id) REFERENCES conteudo(id);
ALTER TABLE red_disciplina ADD CONSTRAINT fk_red_disciplina_red_id FOREIGN KEY (red_id) REFERENCES red(id);
ALTER TABLE red_disciplina ADD CONSTRAINT fk_red_disciplina_disciplina_id FOREIGN KEY (disciplina_id) REFERENCES disciplina(id);
ALTER TABLE conteudo_disciplina ADD CONSTRAINT fk_conteudo_disciplina_conteudo_id FOREIGN KEY (conteudo_id) REFERENCES conteudo(id);
ALTER TABLE conteudo_disciplina ADD CONSTRAINT fk_conteudo_disciplina_disciplina_id FOREIGN KEY (disciplina_id) REFERENCES disciplina(id);
ALTER TABLE autorizacao ADD CONSTRAINT fk_autorizacao_papel_id FOREIGN KEY (papel_id) REFERENCES papel(id);
ALTER TABLE autorizacao ADD CONSTRAINT fk_autorizacao_recurso_id FOREIGN KEY (recurso_id) REFERENCES recurso(id);
ALTER TABLE usuario ADD CONSTRAINT fk_usuario_papel_id FOREIGN KEY (papel_id) REFERENCES papel(id);

/* INDEXES */
CREATE UNIQUE INDEX usuario_email_uidx ON usuario(username);
CREATE UNIQUE INDEX recurso_urn_uidx ON recurso(urn);
