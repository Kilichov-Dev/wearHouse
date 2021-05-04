package uz.pdp.wearhouse.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Result {

    private String meesage;
    private boolean success;
    private Object object;

    public Result(String meesage, boolean success) {
        this.meesage = meesage;
        this.success = success;
    }
}
