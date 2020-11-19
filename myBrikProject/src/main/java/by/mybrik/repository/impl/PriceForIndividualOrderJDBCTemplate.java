package by.mybrik.repository.impl;

import by.mybrik.domain.PriceForIndividualOrder;
import by.mybrik.repository.ColumnsInfo.PriceForIndividualOrderColumns;
import by.mybrik.repository.PriceForIndividualOrderRepository;
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
public class PriceForIndividualOrderJDBCTemplate implements PriceForIndividualOrderRepository {

  private JdbcTemplate jdbcTemplate;
  private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

  public PriceForIndividualOrderJDBCTemplate(
      JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
    this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
  }

  private PriceForIndividualOrder getRowMapper(ResultSet rs, int i) throws SQLException {

    PriceForIndividualOrder priceForOrder = new PriceForIndividualOrder();

    priceForOrder.setId(rs.getLong(PriceForIndividualOrderColumns.ID));
    priceForOrder.setProductType(rs.getString(PriceForIndividualOrderColumns.PRODUCTTYPE));
    priceForOrder.setPrice(rs.getDouble(PriceForIndividualOrderColumns.PRICE));
    priceForOrder.setCreated(rs.getTimestamp(PriceForIndividualOrderColumns.CREATED));
    priceForOrder.setChanged(rs.getTimestamp(PriceForIndividualOrderColumns.CHANGED));
    priceForOrder.setDeleted(rs.getBoolean(PriceForIndividualOrderColumns.ISDELETED));

    return priceForOrder;
  }

  @Override
  public PriceForIndividualOrder save(PriceForIndividualOrder price) {
    final String saveQuery =
        "insert into m_price_for_individual_order ( "
            + "product_type, "
            + "price, "
            + "isdeleted) "
            + "values ( "
            + ":productType, "
            + ":price,"
            + ":isdeleted)";

    GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
    MapSqlParameterSource params = new MapSqlParameterSource();
    params.addValue("productType", price.getProductType());
    params.addValue("price", price.getPrice());
    params.addValue("isdeleted", price.isDeleted());

    namedParameterJdbcTemplate.update(saveQuery, params,keyHolder, new String[] {"id"});

    Long insertedId = Objects.requireNonNull(keyHolder.getKey().longValue());

    return findById(insertedId);
  }

  @Override
  public List<PriceForIndividualOrder> findAll() {
    return jdbcTemplate.query("select * from m_price_for_individual_order", this :: getRowMapper);
  }

  @Override
  public PriceForIndividualOrder findById(Long id) {
    MapSqlParameterSource param = new MapSqlParameterSource();
    param.addValue("id", id);
    return namedParameterJdbcTemplate.queryForObject(
            "select * from m_price_for_individual_order where id = :id", param, this:: getRowMapper);
  }

  @Override
  public Optional<PriceForIndividualOrder> findOne(Long id) {
    return Optional.empty();
  }

  @Override
  public PriceForIndividualOrder update(PriceForIndividualOrder price) {
    return null;
  }

  @Override
  public Long delete(PriceForIndividualOrder price) {
    return null;
  }
}
