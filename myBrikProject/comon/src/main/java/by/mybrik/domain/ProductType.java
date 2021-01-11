package by.mybrik.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@EqualsAndHashCode(exclude = {"textiles"})
public class ProductType {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "product_type")
  private String productType;

  @Column private String photo;

  @Column(name = "is_deleted")
  private boolean isDeleted;

  @Column
  private Timestamp created = new Timestamp(System.currentTimeMillis());

  @Column
  private Timestamp changed = new Timestamp(System.currentTimeMillis());

  @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JoinTable(
      name = "l_textile_product_type",
      joinColumns = @JoinColumn(name = "product_type_id"),
      inverseJoinColumns = @JoinColumn(name = "textile_id"))
  @JsonIgnoreProperties("productTypes")
  private Set<Textile> textiles = Collections.emptySet();
}
