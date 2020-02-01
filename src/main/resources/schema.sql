CREATE TABLE tb_user
(
    id         INTEGER PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(255) NOT NULL,
    last_name  VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE tb_question
(
    id                   INTEGER PRIMARY KEY AUTO_INCREMENT,
    question             VARCHAR(255) NOT NULL,
    answer               VARCHAR(255) NOT NULL,
    is_selectable_answer BOOLEAN      NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE tb_answer_options
(
    question_id   INTEGER      NOT NULL,
    first_option  VARCHAR(255) NOT NULL,
    second_option VARCHAR(255) NOT NULL,
    third_option  VARCHAR(255) NOT NULL,
    fourth_option VARCHAR(255) NOT NULL
);