package by.mybrik.repository.impl;

import by.mybrik.domain.IndividualOrder;
import by.mybrik.repository.ColumnsInfo.IndividualOrderColumns;
import by.mybrik.repository.IndividualOrderRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class IndividualOrderRepositoryJdbcTemplateImpl implements IndividualOrderRepository {

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    private IndividualOrder getUserRowMapper(ResultSet rs, int i) throws SQLException {

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
    public Object save(Object object) {
        return null;
    }

    @Override
    public List findAll() {
        return null;
    }

    @Override
    public Object findById(Object key) {
        return null;
    }

    @Override
    public Optional findOne(Object key) {
        return Optional.empty();
    }

    @Override
    public Object update(Object object) {
        return null;
    }

    @Override
    public Object delete(Object object) {
        return null;
    }
}
