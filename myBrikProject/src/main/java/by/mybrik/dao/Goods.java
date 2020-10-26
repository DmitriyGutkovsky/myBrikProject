package by.mybrik.dao;

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
public class Goods {

    private Long id;

    private String orderCode;

    private String name;

    private  String photo;

    private String gender;

    private String size;

    private String color;

    private boolean isDeleted;

    private double price;

    private int quantity;

    private String category;

    private Timestamp created = new Timestamp(System.currentTimeMillis());

    private Timestamp changed = new Timestamp(System.currentTimeMillis());

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
