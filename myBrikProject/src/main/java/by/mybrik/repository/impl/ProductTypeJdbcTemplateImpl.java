package by.mybrik.repository.impl;

import by.mybrik.domain.ProductType;
import by.mybrik.repository.ColumnsInfo.ProductTypeColumns;
import by.mybrik.repository.ProductTypeRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductTypeJdbcTemplateImpl implements ProductTypeRepository {

    private JdbcTemplate jdbcTemplate;

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public ProductTypeJdbcTemplateImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    private ProductType getRowMapper(ResultSet rs, int i) throws SQLException{

        ProductType type = new ProductType();

        type.setId(rs.getLong(ProductTypeColumns.ID));
        type.setProductType(rs.getString(ProductTypeColumns.PRODUCTTYPE));
        type.setPhoto(rs.getString(ProductTypeColumns.PHOTO));
        type.setDeleted(rs.getBoolean(ProductTypeColumns.ISDELETED));
        type.setCreated(rs.getTimestamp(ProductTypeColumns.CREATED));
        type.setChanged(rs.getTimestamp(ProductTypeColumns.CHANGED));

        return type;

    }

    @Override
    public ProductType save(ProductType object) {
        return null;
    }

    @Override
    public List<ProductType> findAll() {
        return null;
    }

    @Override
    public ProductType findById(Long key) {
        return null;
    }

    @Override
    public Optional<ProductType> findOne(Long key) {
        return Optional.empty();
    }

    @Override
    public ProductType update(ProductType object) {
        return null;
    }

    @Override
    public Long delete(ProductType object) {
        return null;
    }
}
