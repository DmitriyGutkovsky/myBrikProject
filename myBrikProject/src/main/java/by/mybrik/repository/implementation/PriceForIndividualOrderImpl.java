package by.mybrik.repository.implementation;

import by.mybrik.domain.PriceForIndividualOrder;
import by.mybrik.repository.PriceForIndividualOrderRepository;
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
public class PriceForIndividualOrderImpl implements PriceForIndividualOrderRepository {

  private final SessionFactory sessionFactory;

  @Override
  public PriceForIndividualOrder save(PriceForIndividualOrder price) {
    try (final Session session = sessionFactory.openSession()) {
      session.saveOrUpdate(price);
      return price;
    }
  }

  @Override
  public List<PriceForIndividualOrder> findAll() {
    try (Session session = sessionFactory.openSession()) {
      String hqlQuery = "select u from PriceForIndividualOrder u";
      return session.createQuery(hqlQuery, PriceForIndividualOrder.class).list();
    }
  }

  @Override
  public PriceForIndividualOrder findById(Long key) {
    try (Session session = sessionFactory.openSession()) {
      return session.find(PriceForIndividualOrder.class, key);
    }
  }

  @Override
  public Optional<PriceForIndividualOrder> findOne(Long key) {
    return Optional.empty();
  }

  @Override
  public PriceForIndividualOrder update(PriceForIndividualOrder price) {
    try (Session session = sessionFactory.openSession()) {
      Transaction transaction = session.getTransaction();
      transaction.begin();
      session.saveOrUpdate(price);
      transaction.commit();
      return price;
    }
  }

  @Override
  public Long delete(PriceForIndividualOrder price) {
    try (Session session = sessionFactory.openSession()) {
      Transaction transaction = session.getTransaction();
      transaction.begin();
      session.delete(price);
      transaction.commit();
      return price.getId();
    }
  }
}
