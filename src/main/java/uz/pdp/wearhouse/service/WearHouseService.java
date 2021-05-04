package uz.pdp.wearhouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import uz.pdp.wearhouse.entity.WearHouse;
import uz.pdp.wearhouse.payload.Result;
import uz.pdp.wearhouse.repository.WearHouseRepository;

import java.util.List;
import java.util.Optional;
@Service
public class WearHouseService {
    @Autowired
    WearHouseRepository wearHouseRepository;



    public Result addWearHouse(WearHouse wearHouse) {
        boolean exists = wearHouseRepository.existsByName(wearHouse.getName());
        if (exists) {
            return new Result("Bunday wearHouse mavjud!", false);
        }
        wearHouseRepository.save(wearHouse);
        return new Result("Successfully", true);
    }


    public Result editWearHouse( Integer id, WearHouse wearHouse) {
        Optional<WearHouse> optionalwearHouse = wearHouseRepository.findById(id);
        if (!optionalwearHouse.isPresent()) {
            return new Result("wearHouse not found!", false);
        }

        WearHouse wearHouse1 = optionalwearHouse.get();
        boolean exists = wearHouseRepository.existsByName(wearHouse.getName());
        if (exists) {
            return new Result("Bunday wearhouse mavjud!", false);
        }

        wearHouse1.setName(wearHouse.getName());
        wearHouseRepository.save(wearHouse1);
        return new Result("wearHouse editing!", true);
    }

    public Result deleteWearHouse( Integer id) {
        Optional<WearHouse> optionalwearHouse = wearHouseRepository.findById(id);
        if (optionalwearHouse.isPresent()) {
            wearHouseRepository.deleteById(id);
            return new Result("wearHouse deleted!", true);
        }
        return new Result("wearHouse not found!", false);

    }
}
