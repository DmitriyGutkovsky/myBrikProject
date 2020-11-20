package by.mybrik.repository.impl;

import by.mybrik.domain.Textile;
import by.mybrik.repository.ColumnsInfo.TextileColumns;
import by.mybrik.repository.TextileRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
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
    public Textile save(Textile object) {
        return null;
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
    public Textile update(Textile object) {
        return null;
    }

    @Override
    public Long delete(Textile object) {
        return null;
    }
}
