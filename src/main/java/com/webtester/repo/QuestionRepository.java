package com.webtester.repo;

import com.webtester.entity.Question;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.util.List;

public interface QuestionRepository {
    RowMapper<Question> ROW_MAPPER = (ResultSet resultSet, int rowNum) -> {
        return new Question(
                resultSet.getInt("id"),
                resultSet.getString("question"),
                resultSet.getString("answer"),
                resultSet.getBoolean("is_selectable_answer"));
    };

    List<Question> listAllQuestions();

    Question findQuestion(int id);

    Question save(Question question);

    int delete(int id);

    int delete(Question question);
}
