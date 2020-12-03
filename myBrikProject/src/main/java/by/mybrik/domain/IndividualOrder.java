package by.mybrik.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.sql.Timestamp;

@Setter
@Getter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class IndividualOrder {

    private long id;

    private long userId;

    private long textileId;

    private long productTypeId;

    private long priceId;

    private int quantity;

    private double totalPrice;

    private String orderStatus;

    private Timestamp created = new Timestamp(System.currentTimeMillis());

    private Timestamp changed = new Timestamp(System.currentTimeMillis());

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }

}
