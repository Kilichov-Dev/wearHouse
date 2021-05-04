package uz.pdp.wearhouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.wearhouse.entity.Output;

public interface OutputRepository extends JpaRepository<Output, Integer> {
//    boolean existsByFactureNumber(String factureNumber);
    boolean existsByFactureNumberAndWearHouseId(String factureNumber, Integer wearHouse_id);
}
