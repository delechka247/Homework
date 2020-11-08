package ru.itis.javalab.repositories;

import ru.itis.javalab.models.User;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class UsersRepositoryJdbcImpl implements UsersRepository {

    //language=SQL
    public static final String SQL_FIND_ALL = "select * from student";

    //language=SQL
    private static final String SQL_FIND_ALL_BY_AGE = "select * from student where age = ?";

    //language=SQL
    private static final String SQL_FIND_ONE_BY_UUID = "select * from student where uuid = ?";

    //language=SQL
    private static final String SQL_FIND_ONE_BY_EMAIL = "select * from student where email = ?";

    private DataSource dataSource;
    private SimpleJdbcTemplate template;


    public UsersRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
        template = new SimpleJdbcTemplate(dataSource);
    }

    private RowMapper<User> usersRowMapper = row -> User.builder()
            .id(row.getLong("id"))
            .firstName(row.getString("first_name"))
            .lastName(row.getString("last_name"))
            .age(row.getInt("age"))
            .uuid((UUID) row.getObject("uuid"))
            .email(row.getString("email"))
            .hashPassword(row.getString("hash_password"))
            .build();

    @Override
    public void save(User entity) {

    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void delete(User entity) {

    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        return template.query(SQL_FIND_ALL, usersRowMapper);

    }

    @Override
    public List<User> findOneByUUID(UUID uuid) {
        return template.query(SQL_FIND_ONE_BY_UUID, usersRowMapper, uuid);
    }

    @Override
    public List<User> findOneByEmail(String email) {
        return template.query(SQL_FIND_ONE_BY_EMAIL, usersRowMapper, email);
    }

    @Override
    public List<User> findAllByAge(int age) {
        return template.query(SQL_FIND_ALL_BY_AGE, usersRowMapper, age);
    }
}
