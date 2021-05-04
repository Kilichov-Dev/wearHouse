package uz.pdp.wearhouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import uz.pdp.wearhouse.entity.Currency;
import uz.pdp.wearhouse.payload.Result;
import uz.pdp.wearhouse.repository.CurrencyRepository;

import java.util.Optional;
@Service
public class CurrencyService {

    @Autowired
    CurrencyRepository currencyRepository;



    public Result addCurrency(Currency currency) {
        boolean exists = currencyRepository.existsByName(currency.getName());
        if (exists) {
            return new Result("Bunday Currency mavjud!", false);
        }
        currencyRepository.save(currency);
        return new Result("Successfully", true);
    }


    public Result editCurrency( Integer id, Currency currency) {
        Optional<Currency> optionalCurrency = currencyRepository.findById(id);
        if (!optionalCurrency.isPresent()) {
            return new Result("Currency not found!", false);
        }

        Currency currency1 = optionalCurrency.get();
        boolean exists = currencyRepository.existsByName(currency.getName());
        if (exists) {
            return new Result("Bunday currenccy mavjud!", false);
        }

        currency1.setName(currency.getName());
        currencyRepository.save(currency1);
        return new Result("Currency editing!", true);
    }

    public Result deleteCurrency( Integer id) {
        Optional<Currency> optionalCurrency = currencyRepository.findById(id);
        if (optionalCurrency.isPresent()) {
            currencyRepository.deleteById(id);
            return new Result("Currency deleted!", true);
        }
        return new Result("Currency not found!", false);

    }

}
