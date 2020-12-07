package by.mybrik.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.Set;

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

    @ManyToMany
    @JoinTable(name = "m_textile_product_type",
                joinColumns = @JoinColumn(name = "product_type_id"),
                inverseJoinColumns = @JoinColumn(name = "textile_id")
    )
    @JsonIgnoreProperties("types")
    private Set<Textile>  textiles = Collections.emptySet();

}
