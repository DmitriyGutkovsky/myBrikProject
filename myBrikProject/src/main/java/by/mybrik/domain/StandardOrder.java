package by.mybrik.domain;

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
@Table(name = "m_standard_order")
public class StandardOrder {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "good_id")
  private Long goodId;

  @Column(name = "user_id")
  private Long userId;

  @Column private int quantity;

  @Column(name = "total_price")
  private Double totalPrice;

  @Column(name = "order_status")
  private String orderStatus;

  @Column private Timestamp created = new Timestamp(System.currentTimeMillis());

  @Column private Timestamp changed = new Timestamp(System.currentTimeMillis());
}
