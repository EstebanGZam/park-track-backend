INSERT INTO types_of_test (id, type, description) VALUES (1, 'zapateo', 'Prueba que consiste en realizar movimientos rápidos de los pies, alternando pie izquierdo y derecho, con el fin de evaluar la coordinación y velocidad de los movimientos.');
INSERT INTO types_of_test (id, type, description) VALUES (2, 'taconeo', 'Prueba que consiste en golpear el talón contra el suelo de manera repetida para evaluar la fuerza y coordinación del pie y la pierna.');

INSERT INTO types_of_evaluated (id, type) VALUES (1, 'Paciente');
INSERT INTO types_of_evaluated (id, type) VALUES (2, 'Control');

INSERT INTO sexes (id, sex) VALUES (1, 'Masculino');
INSERT INTO sexes (id, sex) VALUES (2, 'Femenino');

-- La contraseña para todos los evaluadores creados y para el administrador es: password
INSERT INTO users (username, password, role) VALUES ('1111222333', '$2a$10$VKtUPg/T5M8FA8o4mnYbR.UI434rY6wgI1F4voiuSi2pUcEoJxKzi', 'EVALUATOR');
INSERT INTO users (username, password, role) VALUES ('3333222111', '$2a$10$VKtUPg/T5M8FA8o4mnYbR.UI434rY6wgI1F4voiuSi2pUcEoJxKzi', 'EVALUATOR');
INSERT INTO users (username, password, role) VALUES ('admin', '$2a$10$VKtUPg/T5M8FA8o4mnYbR.UI434rY6wgI1F4voiuSi2pUcEoJxKzi', 'ADMIN');

INSERT INTO evaluators (id_number, first_name, last_name, email, is_deleted, user_id) VALUES ('1111222333', 'Carlos', 'Gomez', 'cgomez@example.com', false, 1);
INSERT INTO evaluators (id_number, first_name, last_name, email, is_deleted, user_id) VALUES ('3333222111', 'Maria', 'Lopez', 'mlopez@example.com', false, 2);

INSERT INTO evaluated (id_number, first_name, last_name, date_of_birth, email, family_history_parkinson, height, weight, type_of_evaluated_id, sex_id)
VALUES ('1234567890', 'Juan', 'Pérez', TO_DATE('1985-04-12', 'YYYY-MM-DD'), 'juan.perez@example.com', 'S', 1.75, 75.0, (SELECT id FROM types_of_evaluated WHERE type = 'Paciente'), (SELECT id FROM sexes WHERE sex = 'Masculino'));
INSERT INTO evaluated (id_number, first_name, last_name, date_of_birth, email, family_history_parkinson, height, weight, type_of_evaluated_id, sex_id)
VALUES ('1111111111', 'Evaluado', 'De Prueba', TO_DATE('1990-05-22', 'YYYY-MM-DD'), 'evaluado.prueba@example.com', 'N', 1.65, 60.0, (SELECT id FROM types_of_evaluated WHERE type = 'Control'), (SELECT id FROM sexes WHERE sex = 'Femenino'));

INSERT INTO samples (id, evaluated_id, test_type_id, on_off_state, date, aptitude_for_the_test, raw_data)
VALUES
    (1,
     (SELECT id FROM evaluated WHERE id_number = '1234567890'),
     (SELECT id FROM types_of_test WHERE type = 'zapateo'),
     'ON',
     TIMESTAMP '2024-03-15 10:00:00',
     'Apto',
     '{
        "sensors": {
          "sensor1": {
            "sample1": {
              "ax": 16384.0,
              "ay": -8192.0,
              "az": 32768.0,
              "gx": 131.0,
              "gy": -262.0,
              "gz": 524.0,
              "timestamp": 1710489600000
            },
            "sample2": {
              "ax": 24576.0,
              "ay": -4096.0,
              "az": 28672.0,
              "gx": 262.0,
              "gy": -131.0,
              "gz": 393.0,
              "timestamp": 1710489600100
            }
          }
        }
      }'::jsonb
    );

INSERT INTO samples (id, evaluated_id, test_type_id, on_off_state, date, aptitude_for_the_test, raw_data)
VALUES
    (2,
     (SELECT id FROM evaluated WHERE id_number = '1111111111'),
     (SELECT id FROM types_of_test WHERE type = 'taconeo'),
     'OFF',
     TIMESTAMP '2024-03-15 11:00:00',
     'Apto',
     '{
        "sensors": {
          "sensor1": {
            "sample1": {
              "ax": 20480.0,
              "ay": -6144.0,
              "az": 30720.0,
              "gx": 196.0,
              "gy": -196.0,
              "gz": 458.0,
              "timestamp": 1710493200000
            },
            "sample2": {
              "ax": 28672.0,
              "ay": -2048.0,
              "az": 26624.0,
              "gx": 327.0,
              "gy": -65.0,
              "gz": 327.0,
              "timestamp": 1710493200100
            }
          }
        }
      }'::jsonb
    );

INSERT INTO observation_notes (id, sample_evaluated_id, sample_id, sample_test_type_id, description)
VALUES
    (1,
     (SELECT id FROM evaluated WHERE id_number = '1234567890'), -- sample_evaluated_id
     1, -- sample_id
     (SELECT id FROM types_of_test WHERE type = 'zapateo'), -- sample_test_type_id
     'El paciente mostró fatiga después de 30 segundos de la prueba'
    );

INSERT INTO observation_notes (id, sample_evaluated_id, sample_id, sample_test_type_id, description)
VALUES
    (2,
     (SELECT id FROM evaluated WHERE id_number = '0987654321'), -- sample_evaluated_id
     2, -- sample_id
     (SELECT id FROM types_of_test WHERE type = 'taconeo'), -- sample_test_type_id
     'La prueba se completó sin inconvenientes, buena coordinación'
    );
