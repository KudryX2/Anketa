INSERT INTO users (reference, name) VALUES('a1fb7f8b-df6c-427d-8800-73c2a561ec9e', 'kudry');
INSERT INTO users (reference, name) VALUES('49583a1c-3715-4ce3-b5e2-b00f6473d40f', 'paco');
INSERT INTO users (reference, name) VALUES('ae2989c4-494e-4dca-8fa5-a57dc1406aa0', 'sergio');

INSERT INTO surveys (reference, name, owner_id) VALUES('1f954bde-da5c-4348-8f85-23b62633e483','First Anketa', 1L);

INSERT INTO questions (reference, question, survey_id) VALUES('5e6e750d-f8b1-4288-a4cd-d56627e96a39', 'what is favourite programming language?', 1L);
INSERT INTO questions (reference, question, survey_id) VALUES('4663ebf6-5372-4b08-8371-0fe9f92e8b63', 'what is favourite band?', 1L);
INSERT INTO questions (reference, question, survey_id) VALUES('be9fc67d-8a03-4e4f-8b63-d102e7044eaa', 'do you like lobster pizza?', 1L);

INSERT INTO answers (reference, answer, question_id, user_id) VALUES('0cbb9e34-7063-45ad-b15b-b985aaac4df9', 'Java', 1L, 2L);
INSERT INTO answers (reference, answer, question_id, user_id) VALUES('4863eb35-54b2-4c4e-8fe8-b66672ba5e60', 'Maybe Rammstein', 2L, 2L);
INSERT INTO answers (reference, answer, question_id, user_id) VALUES('552b8f35-2ecc-457e-b77f-9a4707e8fcd8', 'Is that a TPS reference?', 3L, 2L);
INSERT INTO answers (reference, answer, question_id, user_id) VALUES('79b0951a-d5ef-414f-931e-b0bd7b710aca', 'Java Script or Python', 1L, 3L);