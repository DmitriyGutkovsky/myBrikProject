package by.mybrik.repository.impl;

import by.mybrik.domain.Goods;
import by.mybrik.repository.CrudRepository;
import by.mybrik.repository.GoodsRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class GoodsRepositoryJdbcTemplateImpl implements GoodsRepository {

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public GoodsRepositoryJdbcTemplateImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }


    @Override
    public Goods save(Goods object) {
        return null;
    }

    @Override
    public List<Goods> findAll() {
        return null;
    }

    @Override
    public Goods findById(Long key) {
        return null;
    }

    @Override
    public Optional<Goods> findOne(Long key) {
        return Optional.empty();
    }

    @Override
    public Goods update(Goods object) {
        return null;
    }

    @Override
    public Long delete(Goods object) {
        return null;
    }
}
