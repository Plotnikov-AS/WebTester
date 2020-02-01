package com.webtester.entity;

import com.webtester.repo.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class QuestionDAO implements QuestionRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public QuestionDAO(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Question> listAllQuestions() {
        return jdbcTemplate.query("select * from tb_question", ROW_MAPPER);
    }

    @Override
    public Question findQuestion(int id) {
        Question question = null;
        try {
            question = jdbcTemplate.queryForObject("select * from tb_question where id = ?", new Object[]{id}, ROW_MAPPER);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return question;
    }

    @Override
    public Question save(Question question) {
        if (question != null) {
            assert  jdbcTemplate.update("insert into tb_question values (?, ?, ?, ?)", question.getId(), question.getQuestion(), question.getAnswer(), question.isSelectable()) > 0;
            return findQuestion(question.getId());
        }
        return null;
    }

    @Override
    public int delete(int id) {
        return jdbcTemplate.update("delete from tb_question where id = ?", id);
    }

    @Override
    public int delete(Question question) {
        return jdbcTemplate.update("delete from tb_question where id = ?", question.getId());
    }
}
