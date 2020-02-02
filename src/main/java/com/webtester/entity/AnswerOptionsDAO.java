package com.webtester.entity;

import com.webtester.repo.AnswerOptionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AnswerOptionsDAO implements AnswerOptionsRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AnswerOptionsDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<String> getQuestionAnswerOptions(Question question) {
        if (question != null && question.isSelectable()) {
            List<String> answers = new ArrayList<>();
            AnswerOptions options = jdbcTemplate.queryForObject("select * from TB_ANSWER_OPTIONS where question_id = ?",
                    new Object[]{question.getId()}, ROW_MAPPER);
            assert options != null;
            answers.add(options.getFirstOption());
            answers.add(options.getSecondOption());
            answers.add(options.getThirdOption());
            answers.add(options.getFourthOption());
            return answers;
        }
        return null;
    }
}
