package by.mybrik.domain.entities;

import by.mybrik.domain.Gender;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "m_users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
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

}
