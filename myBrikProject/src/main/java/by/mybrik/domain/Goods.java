package by.mybrik.domain;

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
@Table(name ="m_goods" )
public class Goods {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_code")
    private String orderCode;

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
