package com.company.repositories;

import com.company.models.User;
import lombok.SneakyThrows;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;


public class UsersRepositoryJdbcTemplateImpl implements UsersRepository {
//    //language=SQL
//    private static final String SQL_INSERT =
//            "insert into shop_user(first_name, last_name, email, hash_password) values (?, ?, ?, ?);";
//
//    //language=SQL
//    private static final String SQL_SELECT_ALL =
//            "select * from shop_user";
//
//    //language=SQL
//    private static final String SQL_SELECT_BY_ID =
//            "select * from shop_user where id = ?";
//
//    //language=SQL
//    private static final String SQL_SELECT_BY_EMAIL =
//            "select * from shop_user where email = ?";
//
//    //language=SQL
//    private static final String SQL_SELECT_BY_COOKIE =
//            "select * from shop_user where id IN (select user_id from auth where cookie_value = ?);";
//
//    private JdbcTemplate jdbcTemplate;
//
//    public UsersRepositoryJdbcTemplateImpl(DataSource dataSource) {
//        this.jdbcTemplate = new JdbcTemplate(dataSource);
//    }
//
//    private RowMapper<User> userRowMapper = (resultSet, i) -> User.builder()
//            .id(resultSet.getLong("id"))
//            .firstName(resultSet.getString("first_name"))
//            .lastName(resultSet.getString("last_name"))
//            .email(resultSet.getString("email"))
//            .hashPassword(resultSet.getString("hash_password"))
//            .build();
//
//    @SneakyThrows
//
////    @Override
////    public Optional<User> findOne(Long id) {
////      //  return Optional.of(jdbcTemplate.queryForObject(SQL_SELECT_BY_ID, userRowMapper, id));
////
////        try {
////            return Optional.of(jdbcTemplate.queryForObject(SQL_SELECT_BY_ID, //&&&&&
////                    userRowMapper, id));
////        } catch (EmptyResultDataAccessException e) {
////            return Optional.empty();
////        }
////    }
//
//    public User findByCookie(String cookie) {
//        return jdbcTemplate.queryForObject(SQL_SELECT_BY_COOKIE, userRowMapper, cookie);
//    }
//
//    @SneakyThrows
//    @Override
//    public void save(User model) {
//        KeyHolder keyHolder = new GeneratedKeyHolder();
//        jdbcTemplate.update(
//                connection -> {
//                    PreparedStatement ps =
//                            connection.prepareStatement(SQL_INSERT, new String[] {"id"});
//
//                    ps.setString(1, model.getFirstName());
//                    ps.setString(2, model.getLastName());
//                    ps.setString(3, model.getEmail());
//                    ps.setString(4, model.getHashPassword());
//                    return ps;
//                }, keyHolder);
//        model.setId(keyHolder.getKey().longValue());
//
//        /*jdbcTemplate.update(SQL_INSERT, model.getFirstName(),model.getLastName(), model.getEmail(), model.getHashPassword())*/;
////
//    }
//
//    @SneakyThrows
//    @Override
//    public void delete(Long id) {
//
//    }
//
//    @SneakyThrows
//    @Override
//    public List<User> findAll() {
//        return jdbcTemplate.query(SQL_SELECT_ALL, userRowMapper);
//    }
//
//    @Override
//    public void update(User model){
//
//    }
//
//    @SneakyThrows
//    @Override
//    public Optional<User> findByEmail(String email) {
////        try {
////            return jdbcTemplate.queryForObject(SQL_SELECT_BY_NAME, userRowMapper, name);
////        } catch (EmptyResultDataAccessException e) {
////            return null;
////        }
//        try {
//            return Optional.of(jdbcTemplate.queryForObject(SQL_SELECT_BY_EMAIL, userRowMapper, email));
//        } catch (EmptyResultDataAccessException e) {
//            return Optional.empty();
//        }
//    }
//
//    @Override
//    @SneakyThrows
//    public boolean addProduct(User user, Product product) {
//        return statement.execute("INSERT INTO basket_product VALUES ('" + product.getId() + "','" + user.getId() + "')");
//    }
//
//

    //language=SQL
    private static final String SQL_INSERT =
            "insert into shop_user(firstname, lastname, patronymic, email, password, phonenumber) values (?, ?, ?, ?, ?, ?);";

    //language=SQL
    private static final String SQL_SELECT_ALL =
            "select * from shop_user";

    //language=SQL
    private static final String SQL_SELECT_BY_ID =
            "select * from shop_user where user_id = ?";

    //language=SQL
    private static final String SQL_SELECT_BY_EMAIL =
            "select * from shop_user where email = ?";

    //language=SQL
    private static final String SQL_SELECT_BY_COOKIE =
            "select * from shop_user where user_id IN (select user_id from auth where cookie_value = ?);";

    private JdbcTemplate jdbcTemplate;

    public UsersRepositoryJdbcTemplateImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    private Statement statement;

    private RowMapper<User> userRowMapper = (resultSet, i) -> User.builder()
            .userID(resultSet.getLong("user_id"))
            .firstName(resultSet.getString("firstname"))
            .lastName(resultSet.getString("lastname"))
            .patronymic(resultSet.getString("patronymic"))
            .email(resultSet.getString("email"))
            .phoneNumber(resultSet.getString("phonenumber"))
            .hashPassword(resultSet.getString("password"))
            .build();

//    @SneakyThrows
//    @Override
//    public Optional<User> findOne(Long id) {
//        //  return Optional.of(jdbcTemplate.queryForObject(SQL_SELECT_BY_ID, userRowMapper, id));
//
//        try {
//            return Optional.of(jdbcTemplate.queryForObject(SQL_SELECT_BY_ID, //&&&&&
//                    userRowMapper, id));
//        } catch (EmptyResultDataAccessException e) {
//            return Optional.empty();
//        }
//    }

    public User findByCookie(String cookie) {
        return jdbcTemplate.queryForObject(SQL_SELECT_BY_COOKIE, userRowMapper, cookie);
    }

    @SneakyThrows
    @Override
    public void save(User model) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                connection -> {
                    PreparedStatement ps =
                            connection.prepareStatement(SQL_INSERT, new String[] {"user_id"});

                    ps.setString(1, model.getFirstName());
                    ps.setString(2, model.getLastName());
                    ps.setString(3, model.getPatronymic());
                    ps.setString(4, model.getEmail());
                    ps.setString(5, model.getHashPassword());
                    ps.setString(6, model.getPhoneNumber());
                    return ps;
                }, keyHolder);
        model.setUserID(keyHolder.getKey().longValue());


        /*jdbcTemplate.update(SQL_INSERT, model.getFirstName(),model.getLastName(), model.getEmail(), model.getHashPassword())*/;
//
    }

    @SneakyThrows
    @Override
    public void delete(Long id) {

    }

    @SneakyThrows
    @Override
    public List<User> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL, userRowMapper);
    }



    @Override
    public void update(User model){

    }

    @SneakyThrows
    @Override
    public Optional<User> findByEmail(String email) {
        try {
            return Optional.of(jdbcTemplate.queryForObject(SQL_SELECT_BY_EMAIL, userRowMapper, email));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

}
