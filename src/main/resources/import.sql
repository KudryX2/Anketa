INSERT INTO users (name) VALUES('kudry');
INSERT INTO users (name) VALUES('paco');
INSERT INTO users (name) VALUES('sergio');

INSERT INTO surveys (reference, name, owner_id) VALUES('1f954bde-da5c-4348-8f85-23b62633e483','First Anketa', 1L);

INSERT INTO questions (question, survey_id) VALUES('what is favourite programming language?', 1L);
INSERT INTO questions (question, survey_id) VALUES('what is favourite band?', 1L);
INSERT INTO questions (question, survey_id) VALUES('do you like lobster pizza?', 1L);

INSERT INTO answers (answer, question_id, user_id) VALUES('Java', 1L, 2L);
INSERT INTO answers (answer, question_id, user_id) VALUES('Maybe Rammstein', 2L, 2L);
INSERT INTO answers (answer, question_id, user_id) VALUES('Is that a TPS reference?', 3L, 2L);
INSERT INTO answers (answer, question_id, user_id) VALUES('Java Script or Python', 1L, 3L);