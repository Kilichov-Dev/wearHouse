package uz.pdp.wearhouse.payload;

import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Data
public class UserDto {
    private String lastName;
    private String phoneNumber;
    private String firstName;
    private String code = UUID.randomUUID().toString();
    private String password;
    private boolean active = true;
    private Set<Integer> wearHouse_id;
}
