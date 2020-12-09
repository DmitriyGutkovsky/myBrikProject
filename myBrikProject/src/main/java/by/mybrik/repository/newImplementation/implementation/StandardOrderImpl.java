package by.mybrik.repository.newImplementation.implementation;

import by.mybrik.domain.entities.StandardOrder;
import by.mybrik.repository.newImplementation.StandardOrderRep;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Primary
@RequiredArgsConstructor
public class StandardOrderImpl implements StandardOrderRep {

  private final SessionFactory sessionFactory;

  @Override
  public StandardOrder save(StandardOrder order) {
    try (Session session = sessionFactory.openSession()) {
      session.saveOrUpdate(order);
      return order;
    }
  }

  @Override
  public List<StandardOrder> findAll() {
    try (Session session = sessionFactory.openSession()) {
      String hqlQuery = "select u from StandardOrder u";
      return session.createQuery(hqlQuery, StandardOrder.class).list();
    }
  }

  @Override
  public StandardOrder findById(Long key) {
    try (Session session = sessionFactory.openSession()) {
      return session.find(StandardOrder.class, key);
    }
  }

  @Override
  public Optional<StandardOrder> findOne(Long key) {
    return Optional.empty();
  }

  @Override
  public StandardOrder update(StandardOrder order) {
    try (Session session = sessionFactory.openSession()) {
      Transaction transaction = session.getTransaction();
      transaction.begin();
      session.saveOrUpdate(order);
      transaction.commit();
      return order;
    }
  }

  @Override
  public Long delete(StandardOrder order) {
    try (Session session = sessionFactory.openSession()) {
      Transaction transaction = session.getTransaction();
      transaction.begin();
      session.delete(order);
      transaction.commit();
      return order.getId();
    }
  }
}
