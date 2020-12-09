package by.mybrik.repository.implementation;

import by.mybrik.domain.entities.Users;
import by.mybrik.repository.UsersRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Primary
public class UsersImpl implements UsersRepository {

  private final SessionFactory sessionFactory;

  public UsersImpl(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  @Override
  public Users save(Users user) {
    try(Session session = sessionFactory.openSession()){
      session.saveOrUpdate(user);
      return user;
    }
  }

  @Override
  public List<Users> findAll() {
    try (Session session = sessionFactory.openSession()) {
      String hqlQuery = "select u from Users u";
      return session.createQuery(hqlQuery, Users.class).list();
    }
  }

  @Override
  public Users findById(Long id) {
    try (Session session = sessionFactory.openSession()) {
      return session.find(Users.class, id);
    }
  }

  @Override
  public Optional<Users> findOne(Long id) {
    return Optional.empty();
  }

  @Override
  public Users update(Users user) {
    try(Session session = sessionFactory.openSession()){
      Transaction transaction = session.getTransaction();
      transaction.begin();
      session.saveOrUpdate(user);
      transaction.commit();
      return user;
    }
  }

  @Override
  public Long delete(Users user) {
    try(Session session = sessionFactory.openSession()){
      Transaction transaction = session.getTransaction();
      transaction.begin();
      session.delete(user);
      transaction.commit();
      return user.getId();
    }
  }
}
