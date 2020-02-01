package com.webtester.entity;

import com.webtester.entity.User;
import com.webtester.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDAO implements UserRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<User> listAllUsers() {
        return jdbcTemplate.query("select * from tb_user", ROW_MAPPER);
    }

    @Override
    public User findUser(int id) {
        User user = null;
        try {
            user = jdbcTemplate.queryForObject("select * from tb_user where id = ?", new Object[]{id}, ROW_MAPPER);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User save(User user) {
        if (user != null) {
            assert jdbcTemplate.update("insert into tb_user values (?, ?, ?)", user.getId(), user.getFirstName(), user.getLastName()) > 0;
            return findUser(user.getId());
        }
        return null;
    }

    @Override
    public int delete(int id) {
        return jdbcTemplate.update("delete from tb_user where id = ?", id);
    }

    @Override
    public int delete(User user) {
        return jdbcTemplate.update("delete from tb_user where id = ?", user.getId());
    }
}
