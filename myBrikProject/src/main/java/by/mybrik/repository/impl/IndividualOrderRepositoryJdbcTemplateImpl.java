package by.mybrik.repository.impl;

import by.mybrik.domain.IndividualOrder;
import by.mybrik.repository.ColumnsInfo.IndividualOrderColumns;
import by.mybrik.repository.IndividualOrderRepository;
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
public class IndividualOrderRepositoryJdbcTemplateImpl implements IndividualOrderRepository {

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public IndividualOrderRepositoryJdbcTemplateImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    private IndividualOrder getIndividualOrderRowMapper(ResultSet rs, int i) throws SQLException {

        IndividualOrder order = new IndividualOrder();

        order.setId(rs.getLong(IndividualOrderColumns.ID));
        order.setUserId(rs.getLong(IndividualOrderColumns.USERID));
        order.setTextileId(rs.getLong(IndividualOrderColumns.TEXTILEID));
        order.setProductTypeId(rs.getLong(IndividualOrderColumns.PRODUCTTYPEID));
        order.setPriceId(rs.getLong(IndividualOrderColumns.PRICEID));
        order.setQuantity(rs.getInt(IndividualOrderColumns.QUANTITY));
        order.setTotalprice(rs.getDouble(IndividualOrderColumns.TOTALPRICE));
        order.setOrderStatus(rs.getString(IndividualOrderColumns.ORDERSTATUS));
        order.setCreated(rs.getTimestamp(IndividualOrderColumns.CREATED));
        order.setChanged(rs.getTimestamp(IndividualOrderColumns.CHANGED));

        return order;
    }


    @Override
    public IndividualOrder save(IndividualOrder order) {
        final String saveQuery =
                "insert into m_individual_order (user_id, textile_id, product_type_id, price_id, quantity, total_price, order_status) "
                        + "values (:userId, :textileId, :productTypeId, :priceId, :quantity, :totalPrice, :orderStatus)";

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("userId", order.getUserId());
        params.addValue("textileId", order.getTextileId());
        params.addValue("productTypeId", order.getProductTypeId());
        params.addValue("priceId", order.getPriceId());
        params.addValue("quantity", order.getQuantity());
        params.addValue("totalPrice", order.getTotalprice());
        params.addValue("orderStatus", order.getOrderStatus());

        namedParameterJdbcTemplate.update(saveQuery, params, keyHolder, new String[] {"id"});

        long insertedId = Objects.requireNonNull(keyHolder.getKey()).longValue();

        return findById(insertedId);
    }

    @Override
    public List<IndividualOrder> findAll() {
        return jdbcTemplate.query("select * from m_individual_order", this::getIndividualOrderRowMapper);
    }

    @Override
    public IndividualOrder findById(Long id) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("orderId", id);
        return namedParameterJdbcTemplate.queryForObject(
                "select * from m_individual_order where id = :orderId", mapSqlParameterSource, this::getIndividualOrderRowMapper);
    }

    @Override
    public Optional<IndividualOrder> findOne(Long key) {
        return Optional.empty();
    }

    @Override
    public IndividualOrder update(IndividualOrder order) {

        final String updateQuery =
                "update m_individual_order "
                        + "set "
                        + "user_id = :userId, "
                        + "textile_id = :textileId, "
                        + "product_type_id = :productTypeId, "
                        + "price_id = :priceId, "
                        + "quantity = :quantity, "
                        + "total_price = :totalPrice, "
                        + "order_status = :orderStatus "
                        + "where id = :id";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("userId", order.getUserId());
        params.addValue("textileId", order.getTextileId());
        params.addValue("productTypeId", order.getProductTypeId());
        params.addValue("priceId", order.getPriceId());
        params.addValue("quantity", order.getQuantity());
        params.addValue("totalPrice", order.getTotalprice());
        params.addValue("orderStatus", order.getOrderStatus());
        params.addValue("id", order.getId());

        namedParameterJdbcTemplate.update(updateQuery, params);

        return findById(order.getId());

    }

    @Override
    public Long delete(IndividualOrder order) {
        return null;
    }
}
