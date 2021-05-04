package uz.pdp.wearhouse.payload;

import lombok.Data;

import java.sql.Timestamp;
import java.util.UUID;

@Data
public class InputDto {

    private Timestamp date;

    private String factureNumber;

    private Integer wearHouse_id;

    private Integer supplier_id;

    private Integer currency_id;

}
