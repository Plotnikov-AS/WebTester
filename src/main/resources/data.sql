INSERT INTO tb_user VALUES
((select max(id) from tb_user) + 1, 'Jack', 'Daniels'),
((select max(id) from tb_user) + 1, 'George', 'Dickel');

INSERT INTO tb_question VALUES
((select max(id) from tb_question) + 1, '2+2', '4', false),
((select max(id) from tb_question) + 1, 'Первый президент России', 'Борис Николаевич Ельцин', true);

INSERT INTO tb_answer_options VALUES
(2, 'первый', 'Борис Николаевич Ельцин', 'третий', 'четвертый');
