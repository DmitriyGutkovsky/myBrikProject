package by.mybrik.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.sql.Timestamp;

@Setter
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class PriceForIndividualOrder {

    private long id;

    private String productType;

    private double price;

    private Timestamp created = new Timestamp(System.currentTimeMillis());

    private Timestamp changed = new Timestamp(System.currentTimeMillis());

    private boolean isDeleted;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }

}
