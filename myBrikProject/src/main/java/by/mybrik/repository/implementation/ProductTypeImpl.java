package by.mybrik.repository.implementation;

import by.mybrik.domain.ProductType;
import by.mybrik.repository.ProductTypeRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Primary
public class ProductTypeImpl implements ProductTypeRepository {

  private final SessionFactory sessionFactory;

  public ProductTypeImpl(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  @Override
  public ProductType save(ProductType type) {
    try (Session session = sessionFactory.openSession()) {
      session.saveOrUpdate(type);
      return type;
    }
  }

  @Override
  public List<ProductType> findAll() {
    try (Session session = sessionFactory.openSession()) {
      String hqlQuery = "select u from ProductType u";
      return session.createQuery(hqlQuery, ProductType.class).list();
    }
  }

  @Override
  public ProductType findById(Long key) {
    try (Session session = sessionFactory.openSession()) {
      return session.find(ProductType.class, key);
    }
  }

  @Override
  public Optional<ProductType> findOne(Long key) {
    return Optional.empty();
  }

  @Override
  public ProductType update(ProductType type) {
    try (Session session = sessionFactory.openSession()) {
      Transaction transaction = session.getTransaction();
      transaction.begin();
      session.saveOrUpdate(type);
      transaction.commit();
      return type;
    }
  }

  @Override
  public Long delete(ProductType type) {
    try (Session session = sessionFactory.openSession()) {
      Transaction transaction = session.getTransaction();
      transaction.begin();
      session.delete(type);
      transaction.commit();
      return type.getId();
    }
  }
}
