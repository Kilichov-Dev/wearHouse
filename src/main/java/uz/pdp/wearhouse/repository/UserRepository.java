package uz.pdp.wearhouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.wearhouse.entity.Users;
import uz.pdp.wearhouse.entity.WearHouse;

import java.util.Collection;
import java.util.Set;

public interface UserRepository extends JpaRepository<Users, Integer> {

    boolean existsByPhoneNumber(String phoneNumber);

//    boolean existsByPasswordAndWearHouseSet(String password, Set<WearHouse> wearHouseSet);

}
