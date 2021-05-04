package uz.pdp.wearhouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.wearhouse.entity.Currency;
import uz.pdp.wearhouse.payload.Result;
import uz.pdp.wearhouse.repository.CurrencyRepository;
import uz.pdp.wearhouse.service.CurrencyService;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/currency")
public class CurrencyController {
    @Autowired
    CurrencyService currencyService;

    @Autowired
    CurrencyRepository currencyRepository;

    @GetMapping
    public List<Currency> getCurrencys() {
        List<Currency> CurrencyList = currencyRepository.findAll();
        return CurrencyList;
    }

    @GetMapping("/{id}")
    public Currency getCurrency(@PathVariable Integer id) {
        Optional<Currency> optionalCurrency = currencyRepository.findById(id);
        if (optionalCurrency.isPresent()) {
            Currency currency = optionalCurrency.get();
            return currency;
        }
        return new Currency();
    }

    @PostMapping
    public Result addCurrency(@RequestBody Currency currency) {
        return currencyService.addCurrency(currency);
    }

    @PutMapping("{id}")
    public Result editCurrency(@PathVariable Integer id, @RequestBody Currency currency) {
        return currencyService.editCurrency(id, currency);
    }

    @DeleteMapping("{id}")
    public Result deleteCurrency(@PathVariable Integer id) {
        return currencyService.deleteCurrency(id);
    }
}
