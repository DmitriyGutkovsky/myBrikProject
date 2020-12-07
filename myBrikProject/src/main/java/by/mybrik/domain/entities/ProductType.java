package by.mybrik.domain.entities;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "m_product_type")
public class ProductType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_type")
    private String productType;

    @Column
    private String photo;

    @Column(name = "is_deleted")
    private boolean isDeleted;

    @Column
    private Timestamp created;

    @Column
    private Timestamp changed;

}
