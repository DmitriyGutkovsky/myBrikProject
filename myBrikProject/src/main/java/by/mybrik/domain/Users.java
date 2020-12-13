package by.mybrik.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.Set;

@Data
@RequiredArgsConstructor
@Entity
@EqualsAndHashCode(exclude = "roles")
@Table(name = "m_users")
public class Users {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column private String name;

  @Column(name = "surname")
  private String surName;

  @Column
  private String login;

  @Column
  private String password;

  @Column
  private String email;

  @Column
  @Enumerated(EnumType.STRING)
  private Gender gender;

  @Column
  private Timestamp created = new Timestamp(System.currentTimeMillis());

  @Column
  private Timestamp changed = new Timestamp(System.currentTimeMillis());

  @Column
  private String phone;

  @Column
  private String address;

  @Column(name = "is_deleted")
  private boolean isDeleted;

  @OneToMany(
      mappedBy = "user",
      cascade = CascadeType.ALL,
      fetch = FetchType.EAGER,
      orphanRemoval = true)
  @JsonManagedReference
  private Set<Role> roles = Collections.emptySet();
}
