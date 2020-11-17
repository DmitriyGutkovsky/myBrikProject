package by.mybrik.repository.impl;

import by.mybrik.domain.IndividualOrder;
import by.mybrik.domain.StandardOrder;
import by.mybrik.repository.ColumnsInfo.IndividualOrderColumns;
import by.mybrik.repository.ColumnsInfo.StandardOrderColumns;
import by.mybrik.repository.StandardOrderRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
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
        return null;
    }

    @Override
    public List<StandardOrder> findAll() {
        return null;
    }

    @Override
    public StandardOrder findById(Long id) {
        return null;
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
