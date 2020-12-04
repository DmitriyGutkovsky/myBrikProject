package by.mybrik.repository.impl;

import by.mybrik.domain.Textile;
import by.mybrik.repository.ColumnsInfo.TextileColumns;
import by.mybrik.repository.TextileRepository;
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
public class TextileJdbcTemplateImpl implements TextileRepository {

    private JdbcTemplate jdbcTemplate;

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public TextileJdbcTemplateImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    private Textile getRowMapper(ResultSet rs, int i) throws SQLException{

        Textile textile = new Textile();

        textile.setId(rs.getLong(TextileColumns.ID));
        textile.setCode(rs.getString(TextileColumns.CODE));
        textile.setName(rs.getString(TextileColumns.NAME));
        textile.setColor(rs.getString(TextileColumns.COLOR));
        textile.setDescription(rs.getString(TextileColumns.DESCRIPTION));
        textile.setPhoto(rs.getString(TextileColumns.PHOTO));
        textile.setDeleted(rs.getBoolean(TextileColumns.ISDELETED));
        textile.setCreated(rs.getTimestamp(TextileColumns.CREATED));
        textile.setChanged(rs.getTimestamp(TextileColumns.CHANGED));

        return textile;
    }


    @Override
    public Textile save(Textile textile) {

    final String saveQuery =
        "insert into m_textile ( "
            + "code, "
            + "name, "
            + "color, "
            + "description, "
            + "photo, "
            + "is_deleted) "
            + "values ("
            + ":code, "
            + ":name, "
            + ":color, "
            + ":description, "
            + ":photo, "
            + ":isDeleted)";

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("code", textile.getCode());
        params.addValue("name", textile.getName());
        params.addValue("color", textile.getColor());
        params.addValue("description", textile.getDescription());
        params.addValue("photo", textile.getPhoto());
        params.addValue("isDeleted", textile.isDeleted());

        namedParameterJdbcTemplate.update(saveQuery, params, keyHolder, new String[] {"id"});

        long insertedId = Objects.requireNonNull(keyHolder.getKey()).longValue();

        return findById(insertedId);
    }

    @Override
    public List<Textile> findAll() {
        return jdbcTemplate.query("select * from m_textile", this:: getRowMapper);
    }

    @Override
    public Textile findById(Long id) {
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("id", id);
        return namedParameterJdbcTemplate.queryForObject("select * from m_textile where id = :id", param, this::getRowMapper);
    }

    @Override
    public Optional<Textile> findOne(Long key) {
        return Optional.empty();
    }

    @Override
    public Textile update(Textile textile) {
        final String updateQuery =
                "update m_textile "
                        + "set "
                        + "code = :code, "
                        + "name = :name, "
                        + "color = :color, "
                        + "description = :description, "
                        + "photo = :photo, "
                        + "is_deleted = :isDeleted, "
                        + "changed = :changed "
                        + "where id = :id";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("code", textile.getCode());
        params.addValue("name", textile.getName());
        params.addValue("color", textile.getColor());
        params.addValue("description", textile.getDescription());
        params.addValue("photo", textile.getPhoto());
        params.addValue("isDeleted", textile.isDeleted());
        params.addValue("changed", textile.getChanged());
        params.addValue("id", textile.getId());

        namedParameterJdbcTemplate.update(updateQuery, params);

        return findById(textile.getId());
    }

    @Override
    public Long delete(Textile textile) {
        final String deleteQuery = "delete from m_textile where id = :id";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", textile.getId());

        namedParameterJdbcTemplate.update(deleteQuery, params);

        return (Long) params.getValue("id");
    }
}
