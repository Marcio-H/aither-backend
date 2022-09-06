INSERT INTO papel (id, descricao)
VALUES
(1, 'ADMIN');

INSERT INTO recurso_backend (id, descricao)
VALUES
(1, 'RED CREATE');

INSERT INTO recurso_frontend (id, descricao)
VALUES
(1, 'RED VIEW');

INSERT INTO papel_recurso (papel_id, recurso_id, tipo_recurso)
VALUES
(1, 1, 'B'),
(1, 1, 'F');
