package by.mybrik.repository.newImplementation.implementation;

import by.mybrik.domain.entities.Textile;
import by.mybrik.repository.newImplementation.TextileRep;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Primary
@RequiredArgsConstructor
public class TextileImpl implements TextileRep {

    private final SessionFactory sessionFactory;

    @Override
    public Textile save(Textile textile) {
        try(Session session = sessionFactory.openSession()){
            session.saveOrUpdate(textile);
            return textile;
        }
    }

    @Override
    public List<Textile> findAll() {
        try(Session session = sessionFactory.openSession()){
            String hqlQuery = "select u from Textile u";
            return session.createQuery(hqlQuery, Textile.class).list();
        }
    }

    @Override
    public Textile findById(Long key) {
        try(Session session = sessionFactory.openSession()){
            return session.find(Textile.class, key);
        }
    }

    @Override
    public Optional<Textile> findOne(Long key) {
        return Optional.empty();
    }

    @Override
    public Textile update(Textile object) {
        return null;
    }

    @Override
    public Long delete(Textile object) {
        return null;
    }
}
