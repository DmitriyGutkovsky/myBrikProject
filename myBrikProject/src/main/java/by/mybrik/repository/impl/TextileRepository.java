package by.mybrik.repository.impl;

import by.mybrik.domain.Textile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TextileRepository extends JpaRepository<Textile, Long> {

    Textile findByCode(String code);

    List<Textile> findAllByIsDeletedIsFalse();

    Textile findByName(String name);
}
