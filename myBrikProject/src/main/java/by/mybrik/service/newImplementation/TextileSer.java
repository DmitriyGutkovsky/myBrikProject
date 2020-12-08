package by.mybrik.service.newImplementation;


import by.mybrik.domain.entities.Textile;

import java.util.List;
import java.util.Optional;

public interface TextileSer {

    Textile save(Textile textile);

    List<Textile> findAll();

    Textile findById(Long id);

    Optional<Textile> findOne(Long id);

    Textile update(Textile textile);

    Long delete(Textile textile);

}
