package by.mybrik.service.impl;

import by.mybrik.domain.Goods;
import by.mybrik.repository.GoodsRepository;
import by.mybrik.service.GoodsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GoodsServiceImpl implements GoodsService {

    private final GoodsRepository goodsRepository;

    @Override
    public Goods save(Goods product) {
        return goodsRepository.save(product);
    }

    @Override
    public List<Goods> findAll() {
        return goodsRepository.findAll();
    }

    @Override
    public Goods findById(Long id) {
        return goodsRepository.findById(id);
    }

    @Override
    public Optional<Goods> findOne(Long id) {
        return Optional.empty();
    }

    @Override
    public Goods update(Goods product) {
        return goodsRepository.update(product);
    }

    @Override
    public Long delete(Goods product) {
        return goodsRepository.delete(product);
    }
}