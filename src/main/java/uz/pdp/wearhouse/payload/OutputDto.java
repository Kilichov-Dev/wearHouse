package uz.pdp.wearhouse.payload;

import lombok.Data;

import java.sql.Timestamp;
import java.util.UUID;

@Data
public class OutputDto {
    private Timestamp date;
    private Integer wearHouse_id;
    private Integer client_id;
    private Integer currency_id;
    private String factureNumber;
    private String code = UUID.randomUUID().toString();
}
