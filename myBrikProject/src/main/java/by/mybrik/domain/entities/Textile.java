package by.mybrik.domain.entities;

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
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.Set;

@Data
@Entity
@Table(name = "m_textile")
@EqualsAndHashCode(exclude = {
        "types"
})
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
    private Timestamp created = new Timestamp(System.currentTimeMillis());

    @Column
    private Timestamp changed = new Timestamp(System.currentTimeMillis());

    @ManyToMany(mappedBy = "textiles", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnoreProperties("textiles")
    private Set<ProductType> types = Collections.emptySet();
}
