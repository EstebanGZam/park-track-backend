INSERT INTO types_of_test (id, type, description) VALUES (1, 'foot-tapping', 'Prueba que consiste en realizar movimientos rápidos de los pies, alternando pie izquierdo y derecho, con el fin de evaluar la coordinación y velocidad de los movimientos.');
INSERT INTO types_of_test (id, type, description) VALUES (2, 'heel-tapping', 'Prueba que consiste en golpear el talón contra el suelo de manera repetida para evaluar la fuerza y coordinación del pie y la pierna.');

INSERT INTO types_of_evaluated (id, type) VALUES (1, 'Paciente');
INSERT INTO types_of_evaluated (id, type) VALUES (2, 'Control');

INSERT INTO sexes (id, sex) VALUES (1, 'Masculino');
INSERT INTO sexes (id, sex) VALUES (2, 'Femenino');

INSERT INTO users (username, password, role) VALUES ('1111222333', '$2a$10$VKtUPg/T5M8FA8o4mnYbR.UI434rY6wgI1F4voiuSi2pUcEoJxKzi', 'EVALUATOR');
INSERT INTO users (username, password, role) VALUES ('3333222111', '$2a$10$VKtUPg/T5M8FA8o4mnYbR.UI434rY6wgI1F4voiuSi2pUcEoJxKzi', 'EVALUATOR');
INSERT INTO users (username, password, role) VALUES ('admin', '$2a$10$VKtUPg/T5M8FA8o4mnYbR.UI434rY6wgI1F4voiuSi2pUcEoJxKzi', 'ADMIN');

INSERT INTO evaluators (id_number, first_name, last_name, email, is_deleted, user_id) VALUES ('1111222333', 'Carlos', 'Gomez', 'cgomez@example.com', false, 1);
INSERT INTO evaluators (id_number, first_name, last_name, email, is_deleted, user_id) VALUES ('3333222111', 'Maria', 'Lopez', 'mlopez@example.com', false, 2);

INSERT INTO evaluated (id_number, first_name, last_name, date_of_birth, email, family_history_parkinson, height, weight, type_of_evaluated_id, sex_id)
VALUES ('1234567890', 'Juan', 'Pérez', TO_DATE('1985-04-12', 'YYYY-MM-DD'), 'juan.perez@example.com', 'N', 1.75, 75.0, (SELECT id FROM types_of_evaluated WHERE type = 'Paciente'), (SELECT id FROM sexes WHERE sex = 'Masculino'));
INSERT INTO evaluated (id_number, first_name, last_name, date_of_birth, email, family_history_parkinson, height, weight, type_of_evaluated_id, sex_id)
VALUES ('0987654321', 'María', 'Gómez', TO_DATE('1990-05-22', 'YYYY-MM-DD'), 'maria.gomez@example.com', 'S', 1.65, 60.0, (SELECT id FROM types_of_evaluated WHERE type = 'Control'), (SELECT id FROM sexes WHERE sex = 'Femenino'));

