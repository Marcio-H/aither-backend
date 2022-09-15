INSERT INTO papel (id, descricao)
VALUES
(1, 'ADMIN');

INSERT INTO recurso (id, urn, tipo)
VALUES
(1, '/red/create', 'BACKEND'),
(2, '/red', 'FRONTEND');

INSERT INTO autorizacao (papel_id, recurso_id)
VALUES
(1, 1),
(1, 2);
