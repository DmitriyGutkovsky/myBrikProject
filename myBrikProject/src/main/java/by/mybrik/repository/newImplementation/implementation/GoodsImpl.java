package by.mybrik.repository.newImplementation.implementation;

import by.mybrik.domain.entities.Goods;
import by.mybrik.repository.newImplementation.GoodsRep;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Primary
public class GoodsImpl implements GoodsRep {

    private final SessionFactory sessionFactory;

    public GoodsImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public Goods save(Goods product) {
    try (final Session session = sessionFactory.openSession()) {
         session.saveOrUpdate(product);
         return product;
        }
    }

    @Override
    public List<Goods> findAll() {
        try(Session session = sessionFactory.openSession()){
            String hqlQuery = "select u from Goods u";
            return session.createQuery(hqlQuery, Goods.class).list();
        }
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
    public Goods update(Goods product) {
        return null;
    }

    @Override
    public Long delete(Goods product) {
        return null;
    }
}
