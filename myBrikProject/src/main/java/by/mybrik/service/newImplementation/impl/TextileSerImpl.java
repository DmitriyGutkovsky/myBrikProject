package by.mybrik.service.newImplementation.impl;

import by.mybrik.domain.entities.Textile;
import by.mybrik.repository.newImplementation.TextileRep;
import by.mybrik.service.newImplementation.TextileSer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TextileSerImpl implements TextileSer {

    private final TextileRep textileRepository;

    @Override
    public Textile save(Textile textile) {
        return textileRepository.save(textile);
    }

    @Override
    public List<Textile> findAll() {
        return textileRepository.findAll();
    }

    @Override
    public Textile findById(Long id) {
        return textileRepository.findById(id);
    }

    @Override
    public Optional<Textile> findOne(Long id) {
        return textileRepository.findOne(id);
    }

    @Override
    public Textile update(Textile textile) {
        return textileRepository.update(textile);
    }

    @Override
    public Long delete(Textile textile) {
        return textileRepository.delete(textile);
    }
}
