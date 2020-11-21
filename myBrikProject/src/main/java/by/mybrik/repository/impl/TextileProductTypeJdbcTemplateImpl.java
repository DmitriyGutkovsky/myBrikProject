package by.mybrik.repository.impl;

import by.mybrik.domain.TextileProductType;
import by.mybrik.repository.ColumnsInfo.TextileProductTypeColumns;
import by.mybrik.repository.TextileProductTypeRepository;
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
        final String saveQuery = "insert into m_textile_product_type (textile_id, product_type_id) "
               + "values ("
               + ":textileId, "
               + ":productTypeId)";

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("textileId",textileProductType.getTextileId());
        params.addValue("productTypeId",textileProductType.getProductTypeId());

        namedParameterJdbcTemplate.update(saveQuery, params, keyHolder, new String[]{"id"});

        long insertedId = Objects.requireNonNull(keyHolder.getKey()).longValue();

        return findById(insertedId);
    }

    @Override
    public List<TextileProductType> findAll() {
        return jdbcTemplate.query("select * from m_textile_product_type", this::getRowMapper);
    }

    @Override
    public TextileProductType findById(Long id) {
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("id", id);
        return namedParameterJdbcTemplate.queryForObject(
                "select * from m_textile_product_type where id = :id",param, this::getRowMapper);
    }

    @Override
    public Optional<TextileProductType> findOne(Long id) {
        return Optional.empty();
    }

    @Override
    public TextileProductType update(TextileProductType textileProductType) {
        final String updatequery = "update m_textile_product_type set "
                + "textile_id = :textileId, "
                + "product_type_id = :productTypeIid "
                + "where id = :id";
        MapSqlParameterSource params = new MapSqlParameterSource();

        params.addValue("id", textileProductType.getId());
        params.addValue("textileId", textileProductType.getTextileId());
        params.addValue("productTypeIid", textileProductType.getProductTypeId());

        namedParameterJdbcTemplate.update(updatequery, params);

        return findById(textileProductType.getId());
    }

    @Override
    public Long delete(TextileProductType textileProductType) {
        return null;
    }
}
