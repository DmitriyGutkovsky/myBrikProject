package by.mybrik.repository.impl;

import by.mybrik.domain.Gender;
import by.mybrik.domain.Users;
import by.mybrik.repository.columnsInfo.UsersColumns;
import by.mybrik.repository.UsersRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class UsersRepositoryJdbcTemplateImpl implements UsersRepository {

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public UsersRepositoryJdbcTemplateImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    private Users getUsersRowMapper(ResultSet rs, int i) throws SQLException {

        Users user = new Users();

        user.setId(rs.getLong(UsersColumns.ID));
        user.setName(rs.getString(UsersColumns.NAME));
        user.setSurName(rs.getString(UsersColumns.SURNAME));
        user.setLogin(rs.getString(UsersColumns.LOGIN));
        user.setPassword(rs.getString(UsersColumns.PASSWORD));
        user.setEmail(rs.getString(UsersColumns.EMAIL));
        user.setEmail(rs.getString(UsersColumns.EMAIL));
        user.setGender(Gender.valueOf(rs.getString(UsersColumns.GENDER)));
        user.setCreated(rs.getTimestamp(UsersColumns.CREATED));
        user.setChanged(rs.getTimestamp(UsersColumns.CHANGED));
        user.setPhone(rs.getInt(UsersColumns.PHONE));
        user.setAddress(rs.getString(UsersColumns.ADDRESS));
        user.setDeleted(rs.getBoolean(UsersColumns.ISDELETED));

        return user;
    }

    @Override
    public Users save(Users user) {
    final String saveQuery =
        "insert into m_users (name, "
            + "surname, "
            + "login, "
            + "password, "
            + "email, "
            + "gender, "
            + "phone, "
            + "address, "
            + "is_deleted) "
            + "values "
            + "(:name, "
            + ":surname, "
            + ":login, "
            + ":password, "
            + ":email, "
            + ":gender, "
            + ":phone, "
            + ":address, "
            + ":isdeleted)";

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource params = new MapSqlParameterSource();

        params.addValue("name", user.getName());
        params.addValue("surname", user.getSurName());
        params.addValue("login", user.getLogin());
        params.addValue("password", user.getPassword());
        params.addValue("email", user.getEmail());
        params.addValue("gender", user.getGender().name());
        params.addValue("phone", user.getPhone());
        params.addValue("address", user.getAddress());
        params.addValue("isdeleted", user.isDeleted());

        namedParameterJdbcTemplate.update(saveQuery, params,keyHolder, new String[] {"id"});

        Long insertedId = Objects.requireNonNull(keyHolder.getKey().longValue());

        return findById(insertedId);
    }

    @Override
    public List<Users> findAll() {
        return jdbcTemplate.query("select * from m_users", this::getUsersRowMapper);
    }

    @Override
    public Users findById(Long id) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("userId", id);
        return namedParameterJdbcTemplate.queryForObject(
                "select * from m_users where id = :userId", mapSqlParameterSource, this::getUsersRowMapper);
    }

    @Override
    public Optional<Users> findOne(Long key) {
        return Optional.empty();
    }

    @Override
    public Users update(Users user) {
        final String updateQuery =
                "update m_users  set "
                        + "name = :name, "
                        + "surname = :surname, "
                        + "login = :login, "
                        + "password = :password, "
                        + "email = :email, "
                        + "gender = :gender, "
                        + "changed = :changed, "
                        + "phone = :phone, "
                        + "address = :address, "
                        + "is_deleted = :isdeleted "
                        +  "where id = :id";

        MapSqlParameterSource params = new MapSqlParameterSource();

        params.addValue("name", user.getName());
        params.addValue("surname", user.getSurName());
        params.addValue("login", user.getLogin());
        params.addValue("password", user.getPassword());
        params.addValue("email", user.getEmail());
        params.addValue("gender", user.getGender().name());
        params.addValue("changed", user.getChanged());
        params.addValue("phone", user.getPhone());
        params.addValue("address", user.getAddress());
        params.addValue("isdeleted", user.isDeleted());
        params.addValue("id", user.getId());

        namedParameterJdbcTemplate.update(updateQuery, params);

    return findById(user.getId());
    }

    @Override
    public Long delete(Users user) {
        final String deleteQuery = "delete from m_users where id = :id";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", user.getId());

        namedParameterJdbcTemplate.update(deleteQuery, params);

        return (Long) params.getValue("id");
    }
}
