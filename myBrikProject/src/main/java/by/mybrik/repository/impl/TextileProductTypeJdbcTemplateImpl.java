package by.mybrik.repository.impl;

import by.mybrik.domain.TextileProductType;
import by.mybrik.repository.ColumnsInfo.TextileProductTypeColumns;
import by.mybrik.repository.TextileProductTypeRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class TextileProductTypeJdbcTemplateImpl implements TextileProductTypeRepository {

    private JdbcTemplate jdbcTemplate;

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public TextileProductTypeJdbcTemplateImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    private TextileProductType getRowMapper(ResultSet rs, int i) throws SQLException{

        TextileProductType textileProductType = new TextileProductType();

        textileProductType.setId(rs.getLong(TextileProductTypeColumns.ID));
        textileProductType.setProductTypeId(rs.getLong(TextileProductTypeColumns.PRODUCTTYPEID));
        textileProductType.setTextileId(rs.getLong(TextileProductTypeColumns.TEXTILEID));

        return textileProductType;
    }

    @Override
    public TextileProductType save(TextileProductType textileProductType) {
        return null;
    }

    @Override
    public List<TextileProductType> findAll() {
        return null;
    }

    @Override
    public TextileProductType findById(Long id) {
        return null;
    }

    @Override
    public Optional<TextileProductType> findOne(Long id) {
        return Optional.empty();
    }

    @Override
    public TextileProductType update(TextileProductType textileProductType) {
        return null;
    }

    @Override
    public Long delete(TextileProductType textileProductType) {
        return null;
    }
}
