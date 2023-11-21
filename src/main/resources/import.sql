INSERT INTO users (name) VALUES('kudry');
INSERT INTO users (name) VALUES('paco');
INSERT INTO users (name) VALUES('sergio');

INSERT INTO surveys (reference, name, owner_id) VALUES('kudry-first-anketa','First Anketa', '1');

INSERT INTO questions (question, survey_id) VALUES('what is favourite programming language?', '1');
INSERT INTO questions (question, survey_id) VALUES('what is favourite band?', '1');
INSERT INTO questions (question, survey_id) VALUES('do you like lobster pizza?', '1');

INSERT INTO answers (answer, question_id, user_id) VALUES('Java', '1', '2');
INSERT INTO answers (answer, question_id, user_id) VALUES('Maybe Rammstein', '2', '2');
INSERT INTO answers (answer, question_id, user_id) VALUES('Is that a TPS reference?', '3', '2');
INSERT INTO answers (answer, question_id, user_id) VALUES('Java Script or Python', '1', '3');