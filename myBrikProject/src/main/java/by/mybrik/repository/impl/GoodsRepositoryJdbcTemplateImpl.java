package by.mybrik.repository.impl;

import by.mybrik.domain.Goods;
import by.mybrik.repository.GoodsRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class GoodsRepositoryJdbcTemplateImpl implements GoodsRepository {

  private JdbcTemplate jdbcTemplate;
  private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

  public GoodsRepositoryJdbcTemplateImpl(
      JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
    this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
  }

  @Override
  public Goods save(Goods product) {
    final String saveQuery =
        "insert into m_goods (orderCode, name, photo, gender, size, color, description, isDeleted, price, quantity, category) "
            + "values (:orderCode, :name, :photo, :gender, :size, :color, :description, :isDeleted, :price, :quantity, :category)";

    GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

    MapSqlParameterSource params = new MapSqlParameterSource();
    params.addValue("orderCode", product.getOrderCode());
    params.addValue("name", product.getName());
    params.addValue("photo", product.getPhoto());
    params.addValue("gender", product.getGender());
    params.addValue("size", product.getSize());
    params.addValue("color", product.getColor());
    params.addValue("description", product.getDescription());
    params.addValue("isDeleted", product.isDeleted());
    params.addValue("price", product.getPrice());
    params.addValue("quantity", product.getQuantity());

    namedParameterJdbcTemplate.update(saveQuery, params, keyHolder, new String[] {"id"});

    long insertedId = Objects.requireNonNull(keyHolder.getKey()).longValue();

    return findById(insertedId);
  }

  @Override
  public List<Goods> findAll() {
    return null;
  }

  @Override
  public Goods findById(Long id) {
    return null;
  }

  @Override
  public Optional<Goods> findOne(Long id) {
    return Optional.empty();
  }

  @Override
  public Goods update(Goods product) {
    return null;
  }

  @Override
  public Long delete(Goods product) {
    return null;
  }
}
