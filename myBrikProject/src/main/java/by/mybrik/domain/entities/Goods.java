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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.sql.Timestamp;
import java.util.Date;

@Data
@Entity
@Table(name ="m_goods" )
public class Goods {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_code")
    @Temporal(TemporalType.DATE)
    private Date orderCode;

    @Column
    private String name;

    @Column
    private  String photo;

    @Column
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column
    private String size;

    @Column
    private String color;

    @Column
    private String description;

    @Column(name = "is_deleted")
    private boolean isDeleted;

    @Column
    private Double price;

    @Column
    private int quantity;

    @Column
    private String category;

    @Column
    private Timestamp created = new Timestamp(System.currentTimeMillis());

    @Column
    private Timestamp changed = new Timestamp(System.currentTimeMillis());

}
