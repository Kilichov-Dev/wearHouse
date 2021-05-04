package uz.pdp.wearhouse.payload;

import lombok.Data;

import java.sql.Date;

@Data
public class InputProductDto {

    private Double amount;

    private Double price;

    private Date expiDate;

    private Integer input_id;

    private Integer product_id;

}
