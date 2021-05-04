package uz.pdp.wearhouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import uz.pdp.wearhouse.entity.Client;
import uz.pdp.wearhouse.entity.Currency;
import uz.pdp.wearhouse.entity.Output;
import uz.pdp.wearhouse.entity.WearHouse;
import uz.pdp.wearhouse.payload.OutputDto;
import uz.pdp.wearhouse.payload.Result;
import uz.pdp.wearhouse.repository.ClientRepository;
import uz.pdp.wearhouse.repository.CurrencyRepository;
import uz.pdp.wearhouse.repository.OutputRepository;
import uz.pdp.wearhouse.repository.WearHouseRepository;

import java.util.Optional;

@Service
public class OutputService {
    @Autowired
    OutputRepository outputRepository;
    @Autowired
    CurrencyRepository currencyRepository;
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    WearHouseRepository wearHouseRepository;

    public Result addOutput( OutputDto outpuDto) {
        Output output = new Output();
        output.setDate(outpuDto.getDate());
        boolean exist = outputRepository.existsByFactureNumberAndWearHouseId(output.getFactureNumber(), outpuDto.getWearHouse_id());
        if (exist) return new Result("This facture numbar already exists!", false);
        output.setFactureNumber(output.getFactureNumber());
        Optional<Client> optionalClient = clientRepository.findById(outpuDto.getClient_id());
        if (!optionalClient.isPresent()) return new Result("Client not found!", false);
        output.setClient(optionalClient.get());
        Optional<Currency> optionalCurrency = currencyRepository.findById(outpuDto.getCurrency_id());
        if (!optionalCurrency.isPresent()) return new Result("Currency not found!", false);
        output.setCurrency(optionalCurrency.get());
        Optional<WearHouse> optionalWearHouse = wearHouseRepository.findById(outpuDto.getWearHouse_id());
        if (!optionalWearHouse.isPresent()) return new Result("WearHouse not found!", false);
        output.setWearHouse(optionalWearHouse.get());
        outputRepository.save(output);
        return new Result("Successfully added!", true);
    }

    public Result editOutput( Integer id,  OutputDto outpuDto) {
        Optional<Output> optionalOutput = outputRepository.findById(id);
        if (optionalOutput.isPresent()) {
            Output output = optionalOutput.get();
            output.setDate(outpuDto.getDate());
            boolean exist = outputRepository.existsByFactureNumberAndWearHouseId(output.getFactureNumber(), outpuDto.getWearHouse_id());
            if (exist) return new Result("This facture numbar already exists!", false);
            output.setFactureNumber(output.getFactureNumber());
            Optional<Client> optionalClient = clientRepository.findById(outpuDto.getClient_id());
            if (!optionalClient.isPresent()) return new Result("Client not found!", false);
            output.setClient(optionalClient.get());
            Optional<Currency> optionalCurrency = currencyRepository.findById(outpuDto.getCurrency_id());
            if (!optionalCurrency.isPresent()) return new Result("Currency not found!", false);
            output.setCurrency(optionalCurrency.get());
            Optional<WearHouse> optionalWearHouse = wearHouseRepository.findById(outpuDto.getWearHouse_id());
            if (!optionalWearHouse.isPresent()) return new Result("WearHouse not found!", false);
            output.setWearHouse(optionalWearHouse.get());
            outputRepository.save(output);
            return new Result("Successfully editing!", true);
        }
        return new Result("Output id not found!", false);
    }

    public Result deletedOutput( Integer id) {
        Optional<Output> optionalOutput = outputRepository.findById(id);
        if (optionalOutput.isPresent()) {
            outputRepository.deleteById(id);
            return new Result("Successfully deleted!", true);
        }
        return new Result("Output nor found!", false);
    }


}
