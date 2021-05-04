package uz.pdp.wearhouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import uz.pdp.wearhouse.entity.Currency;
import uz.pdp.wearhouse.entity.Input;
import uz.pdp.wearhouse.entity.Supplier;
import uz.pdp.wearhouse.entity.WearHouse;
import uz.pdp.wearhouse.payload.InputDto;
import uz.pdp.wearhouse.payload.Result;
import uz.pdp.wearhouse.repository.CurrencyRepository;
import uz.pdp.wearhouse.repository.InputRepository;
import uz.pdp.wearhouse.repository.SupplierRepository;
import uz.pdp.wearhouse.repository.WearHouseRepository;

import java.util.Optional;

@Service
public class InputService {

    @Autowired
    InputRepository inputRepository;

    @Autowired
    WearHouseRepository wearHouseRepository;

    @Autowired
    CurrencyRepository currencyRepository;

    @Autowired
    SupplierRepository supplierRepository;

    public Result addInput( InputDto inputDto) {
        Input input = new Input();
        input.setDate(inputDto.getDate());

        Optional<Currency> optionalCurrency = currencyRepository.findById(inputDto.getCurrency_id());
        if (!optionalCurrency.isPresent()) {
            return new Result("Currency not found!", false);
        }
        input.setCurrency(optionalCurrency.get());
        Optional<Supplier> optionalSupplier = supplierRepository.findById(inputDto.getSupplier_id());
        if (!optionalSupplier.isPresent()) {
            return new Result("Supplier not found!", false);
        }
        input.setSupplier(optionalSupplier.get());
        Optional<WearHouse> optionalWearHouse = wearHouseRepository.findById(inputDto.getWearHouse_id());
        if (!optionalWearHouse.isPresent()) {
            return new Result("WearHouse not found!", false);
        }
        input.setWearHouse(optionalWearHouse.get());
        boolean exists = inputRepository.existsByFactureNumberAndSupplierIdAndWearHouseId(inputDto.getFactureNumber(), inputDto.getSupplier_id(), inputDto.getWearHouse_id());
        if (exists) {

            return new Result("This WearHouse such facture number already exists!", false);

        }
        input.setFactureNumber(inputDto.getFactureNumber());

        inputRepository.save(input);
        return new Result("Successfully!", true);
    }

    public Result editInput( Integer id,  InputDto inputDto) {
        Optional<Input> optionalInput = inputRepository.findById(id);
        if (optionalInput.isPresent()) {
            Input input = optionalInput.get();
            input.setDate(inputDto.getDate());
           Optional<Currency> optionalCurrency = currencyRepository.findById(inputDto.getCurrency_id());
            if (!optionalCurrency.isPresent()) {
                return new Result("Currency not found!", false);
            }
            input.setCurrency(optionalCurrency.get());
            Optional<Supplier> optionalSupplier = supplierRepository.findById(inputDto.getSupplier_id());
            if (!optionalSupplier.isPresent()) {
                return new Result("Supplier not found!", false);
            }
            input.setSupplier(optionalSupplier.get());
            Optional<WearHouse> optionalWearHouse = wearHouseRepository.findById(inputDto.getWearHouse_id());
            if (!optionalWearHouse.isPresent()) {
                return new Result("WearHouse not found!", false);
            }
            input.setWearHouse(optionalWearHouse.get());
            boolean exists = inputRepository.existsByFactureNumberAndSupplierIdAndWearHouseId(inputDto.getFactureNumber(), inputDto.getSupplier_id(), inputDto.getWearHouse_id());
            if (exists) {
                return new Result("This WearHouse such facture number already exists!", false);

            }
            input.setFactureNumber(inputDto.getFactureNumber());


            inputRepository.save(input);
            return new Result("Input editing!", true);

        }
        return new Result("Input not found!", false);
    }

    public Result deleteInput( Integer id) {
        Optional<Input> optionalInput = inputRepository.findById(id);
        if (optionalInput.isPresent()) {

            inputRepository.deleteById(id);
            return new Result("Input deleted!", true);
        }
        return new Result("Input not found!", false);
    }

}
