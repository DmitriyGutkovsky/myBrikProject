package by.mybrik.repository.impl;

import by.mybrik.domain.StandardOrder;
import by.mybrik.repository.ColumnsInfo.StandardOrderColumns;
import by.mybrik.repository.StandardOrderRepository;
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
public class StandardOrderRepositoryJdbcTemplate implements StandardOrderRepository {

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public StandardOrderRepositoryJdbcTemplate(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    private StandardOrder getStandardOrderRowMapper(ResultSet rs, int i) throws SQLException {

        StandardOrder order = new StandardOrder();

        order.setId(rs.getLong(StandardOrderColumns.ID));
        order.setGoodId(rs.getLong(StandardOrderColumns.GOODID));
        order.setUserId(rs.getLong(StandardOrderColumns.USERID));
        order.setQuantity(rs.getInt(StandardOrderColumns.QUANTITY));
        order.setTotalPrice(rs.getDouble(StandardOrderColumns.TOTALPRICE));
        order.setOrderStatus(rs.getString(StandardOrderColumns.ORDERSTATUS));
        order.setChanged(rs.getTimestamp(StandardOrderColumns.CHANGED));
        order.setCreated(rs.getTimestamp(StandardOrderColumns.CREATED));

        return order;
    }

    @Override
    public StandardOrder save(StandardOrder order) {

    final String saveQuery =
        "insert into m_standard_order (good_id, "
            + "user_id, "
            + "quantity ,"
            + "total_price, "
            + "order_status)"
//            + "order_status, "
//            + "changed, "
//            + "created) "
            + "values (:goodId, "
            + ":userId, "
            + ":quantity, "
            + ":totalPrice, "
            + ":orderStatus)";
//            + ":orderStatus, "
//            + ":changed, "
//            + ":created)";

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("goodId",order.getGoodId());
        params.addValue("userId",order.getUserId());
        params.addValue("quantity",order.getQuantity());
        params.addValue("totalPrice",order.getTotalPrice());
        params.addValue("orderStatus",order.getOrderStatus());
//        params.addValue("changed",order.getChanged());
//        params.addValue("created",order.getCreated());

        namedParameterJdbcTemplate.update(saveQuery, params, keyHolder, new String[] {"id"});

        Long insertedId = Objects.requireNonNull(keyHolder.getKey().longValue());

        return findById(insertedId);
    }

    @Override
    public List<StandardOrder> findAll() {
        return jdbcTemplate.query("select * from m_standard_order", this :: getStandardOrderRowMapper);
    }

    @Override
    public StandardOrder findById(Long id) {
        return   jdbcTemplate.queryForObject(
                "select * from m_standard_order where id = ?", new Object[]{id}, this::getStandardOrderRowMapper);
    }

    @Override
    public Optional<StandardOrder> findOne(Long id) {
        return Optional.empty();
    }

    @Override
    public StandardOrder update(StandardOrder order) {
        return null;
    }

    @Override
    public Long delete(StandardOrder order) {
        return null;
    }
}
