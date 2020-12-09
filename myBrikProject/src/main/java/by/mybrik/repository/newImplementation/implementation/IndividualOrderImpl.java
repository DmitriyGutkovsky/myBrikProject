package by.mybrik.repository.newImplementation.implementation;

import by.mybrik.domain.entities.IndividualOrder;
import by.mybrik.repository.newImplementation.IndividualOrderRep;
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
public class IndividualOrderImpl implements IndividualOrderRep {

  private final SessionFactory sessionFactory;

  @Override
  public IndividualOrder save(IndividualOrder order) {
    try (Session session = sessionFactory.openSession()) {
      session.saveOrUpdate(order);
      return order;
    }
  }

  @Override
  public List<IndividualOrder> findAll() {
    try (Session session = sessionFactory.openSession()) {
      String hqlQuery = "select u from IndividualOrder u";
      return session.createQuery(hqlQuery, IndividualOrder.class).list();
    }
  }

  @Override
  public IndividualOrder findById(Long key) {
    try (Session session = sessionFactory.openSession()) {
      return session.find(IndividualOrder.class, key);
    }
  }

  @Override
  public Optional<IndividualOrder> findOne(Long key) {
    return Optional.empty();
  }

  @Override
  public IndividualOrder update(IndividualOrder order) {
    try (Session session = sessionFactory.openSession()) {
      Transaction transaction = session.getTransaction();
      transaction.begin();
      session.saveOrUpdate(order);
      transaction.commit();
      return order;
    }
  }

  @Override
  public Long delete(IndividualOrder order) {
    try (Session session = sessionFactory.openSession()) {
      Transaction transaction = session.getTransaction();
      transaction.begin();
      session.delete(order);
      transaction.commit();
      return order.getId();
    }
  }
}
