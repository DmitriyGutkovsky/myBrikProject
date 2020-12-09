package by.mybrik.repository.implementation;

import by.mybrik.domain.entities.Goods;
import by.mybrik.repository.GoodsRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Primary
public class GoodsImpl implements GoodsRepository {

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
    try (Session session = sessionFactory.openSession()) {
      String hqlQuery = "select u from Goods u";
      return session.createQuery(hqlQuery, Goods.class).list();
    }
  }

  @Override
  public Goods findById(Long key) {
    try (Session session = sessionFactory.openSession()) {
      return session.find(Goods.class, key);
    }
  }

  @Override
  public Optional<Goods> findOne(Long key) {
    return Optional.empty();
  }

  @Override
  public Goods update(Goods product) {
    try (Session session = sessionFactory.openSession()) {
      Transaction transaction = session.getTransaction();
      transaction.begin();
      session.saveOrUpdate(product);
      transaction.commit();
      return product;
    }
  }

  @Override
  public Long delete(Goods product) {
    try (Session session = sessionFactory.openSession()) {
      Transaction transaction = session.getTransaction();
      transaction.begin();
      session.delete(product);
      transaction.commit();
      return product.getId();
    }
  }
}
