package by.mybrik.domain.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "m_textile")
public class Textile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String code;

    @Column
    private String name;

    @Column
    private String color;

    @Column
    private String description;

    @Column
    private String photo;

    @Column(name = "is_deleted")
    private boolean isDeleted;

    @Column
    private Timestamp created;

    @Column
    private Timestamp changed;
}
