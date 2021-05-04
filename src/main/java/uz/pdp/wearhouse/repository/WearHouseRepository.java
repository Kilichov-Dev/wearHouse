package uz.pdp.wearhouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.wearhouse.entity.WearHouse;

public interface WearHouseRepository extends JpaRepository<WearHouse, Integer> {
    boolean existsByName(String name);

}
