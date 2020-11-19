package by.mybrik.repository.impl;

import by.mybrik.domain.PriceForIndividualOrder;
import by.mybrik.repository.ColumnsInfo.PriceForIndividualOrderColumns;
import by.mybrik.repository.PriceForIndividualOrderRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
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
  public PriceForIndividualOrder save(PriceForIndividualOrder object) {
    return null;
  }

  @Override
  public List<PriceForIndividualOrder> findAll() {
    return null;
  }

  @Override
  public PriceForIndividualOrder findById(Long key) {
    return null;
  }

  @Override
  public Optional<PriceForIndividualOrder> findOne(Long key) {
    return Optional.empty();
  }

  @Override
  public PriceForIndividualOrder update(PriceForIndividualOrder object) {
    return null;
  }

  @Override
  public Long delete(PriceForIndividualOrder object) {
    return null;
  }
}
