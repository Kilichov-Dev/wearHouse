package uz.pdp.wearhouse.payload;

import lombok.Data;

@Data
public class OutputProductDto {
    private Double amount;

    private Double price;

    private Integer output_id;

    private Integer product_id;
}
