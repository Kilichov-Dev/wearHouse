package uz.pdp.wearhouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.wearhouse.entity.Currency;

public interface CurrencyRepository extends JpaRepository<Currency, Integer> {
    boolean existsByName(String name);

}
