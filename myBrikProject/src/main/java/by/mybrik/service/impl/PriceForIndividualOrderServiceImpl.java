package by.mybrik.service.impl;

import by.mybrik.domain.PriceForIndividualOrder;
import by.mybrik.repository.PriceForIndividualOrderRepository;
import by.mybrik.service.PriceForIndividualOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PriceForIndividualOrderServiceImpl implements PriceForIndividualOrderService {

    private final PriceForIndividualOrderRepository priceForIndividualOrderRepository;


    @Override
    public PriceForIndividualOrder save(PriceForIndividualOrder price) {
        return priceForIndividualOrderRepository.save(price);
    }

    @Override
    public List<PriceForIndividualOrder> findAll() {
        return priceForIndividualOrderRepository.findAll();
    }

    @Override
    public PriceForIndividualOrder findById(Long id) {
        return priceForIndividualOrderRepository.findById(id);
    }

    @Override
    public Optional<PriceForIndividualOrder> findOne(Long id) {
        return priceForIndividualOrderRepository.findOne(id);
    }

    @Override
    public PriceForIndividualOrder update(PriceForIndividualOrder price) {
        return priceForIndividualOrderRepository.update(price);
    }

    @Override
    public Long delete(PriceForIndividualOrder price) {
        return priceForIndividualOrderRepository.delete(price);
    }
}
