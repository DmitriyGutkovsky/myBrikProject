package by.mybrik.service;


import by.mybrik.domain.Textile;

import java.util.List;
import java.util.Optional;

public interface TextileService {

    Textile save(Textile textile);

    List<Textile> findAll();

    Textile findById(Long id);

    Optional<Textile> findOne(Long id);

    Textile update(Textile textile);

    Long delete(Textile textile);

}
