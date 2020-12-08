package by.mybrik.service.newImplementation.impl;

import by.mybrik.domain.entities.Users;
import by.mybrik.service.newImplementation.UsersSer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Primary
public class UsersSerImpl implements UsersSer {

  private final SessionFactory sessionFactory;

  public UsersSerImpl(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  @Override
  public Users save(Users user) {
    return null;
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
    return null;
  }

  @Override
  public Optional<Users> findOne(Long id) {
    return Optional.empty();
  }

  @Override
  public Users update(Users user) {
    return null;
  }

  @Override
  public Long delete(Users user) {
    return null;
  }
}
