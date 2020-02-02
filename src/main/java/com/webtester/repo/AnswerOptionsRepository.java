package com.webtester.repo;

import com.webtester.entity.AnswerOptions;
import com.webtester.entity.Question;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.util.List;

public interface AnswerOptionsRepository {
    RowMapper<AnswerOptions> ROW_MAPPER = (ResultSet resultSet, int rowNum) -> {
        return new AnswerOptions(
                resultSet.getInt("question_id"),
                resultSet.getString("first_option"),
                resultSet.getString("second_option"),
                resultSet.getString("third_option"),
                resultSet.getString("fourth_option"));
    };

    List<String> getQuestionAnswerOptions(Question question);
}
