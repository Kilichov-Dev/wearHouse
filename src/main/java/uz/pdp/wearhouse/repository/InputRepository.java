package uz.pdp.wearhouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.wearhouse.entity.Input;

@Repository
public interface InputRepository extends JpaRepository<Input, Integer> {
//    boolean existsByFactureNumber(String factureNumber);
    boolean existsByFactureNumberAndSupplierIdAndWearHouseId(String factureNumber, Integer supplier_id, Integer wearHouse_id);

}
