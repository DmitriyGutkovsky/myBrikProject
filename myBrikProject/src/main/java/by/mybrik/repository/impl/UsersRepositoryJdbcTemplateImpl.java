package by.mybrik.repository.impl;

import by.mybrik.domain.Gender;
import by.mybrik.domain.Users;
import by.mybrik.repository.ColumnsInfo.UsersColumns;
import by.mybrik.repository.UsersRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
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
    public Users save(Users object) {
        return null;
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
    public Users update(Users object) {
        return null;
    }

    @Override
    public Long delete(Users object) {
        return null;
    }
}
