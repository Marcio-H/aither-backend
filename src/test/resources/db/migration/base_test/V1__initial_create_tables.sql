CREATE TABLE usuario (
	id SERIAL,
	email VARCHAR(125) NOT NULL,
	senha VARCHAR(125) NOT NULL,
	nome VARCHAR(125) NOT NULL
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
    endereco VARCHAR(255),
    criado_por INTEGER
);

/* PRIMARY KEYS */
ALTER TABLE usuario ADD CONSTRAINT pk_usuario_id PRIMARY KEY (id);
ALTER TABLE disciplina ADD CONSTRAINT pk_disciplina_id PRIMARY KEY (id);
ALTER TABLE conteudo ADD CONSTRAINT pk_conteudo_id PRIMARY KEY (id);
ALTER TABLE red ADD CONSTRAINT pk_red_id PRIMARY KEY (id);

/* FOREIGN KEYS */
ALTER TABLE red ADD CONSTRAINT fk_red_usuario FOREIGN KEY (criado_por) REFERENCES usuario(id);

/* INDEXES */
CREATE UNIQUE INDEX usuario_email_uidx ON usuario(email);
