package by.mybrik.domain.entities;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "m_price_for_individual_order")
public class PriceForIndividualOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_type")
    private String productType;

    @Column
    private Double price;

    @Column
    private Timestamp created = new Timestamp(System.currentTimeMillis());

    @Column
    private Timestamp changed = new Timestamp(System.currentTimeMillis());

    @Column(name = "is_deleted")
    private boolean isDeleted;
}
