package com.webtester.repo;

import com.webtester.entity.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.util.List;

public interface UserRepository {
    RowMapper<User> ROW_MAPPER = (ResultSet resultSet, int rowNum) -> {
        return new User(
                resultSet.getInt("id"),
                resultSet.getString("first_name"),
                resultSet.getString("last_name"));
    };

    List<User> listAllUsers();

    User findUser(int id);

    User save(User user);

    int delete(User user);

    int delete(int id);
}
