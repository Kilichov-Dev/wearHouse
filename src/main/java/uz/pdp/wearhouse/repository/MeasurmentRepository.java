package uz.pdp.wearhouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.wearhouse.entity.Measurement;

@Repository
public interface MeasurmentRepository extends JpaRepository<Measurement, Integer> {
    boolean existsByName(String name);
}
