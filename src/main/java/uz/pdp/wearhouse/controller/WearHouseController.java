package uz.pdp.wearhouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.wearhouse.entity.WearHouse;
import uz.pdp.wearhouse.entity.WearHouse;
import uz.pdp.wearhouse.payload.Result;
import uz.pdp.wearhouse.repository.WearHouseRepository;
import uz.pdp.wearhouse.service.WearHouseService;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/wearHouse")
public class WearHouseController {

    @Autowired
    WearHouseService wearHouseService;
    @Autowired
    WearHouseRepository wearHouseRepository;


    @GetMapping
    public List<WearHouse> getWearHouses() {
        List<WearHouse> wearHouseList = wearHouseRepository.findAll();
        return wearHouseList;
    }

    @GetMapping("/{id}")
    public WearHouse getWearHouse(@PathVariable Integer id) {
        Optional<WearHouse> optionalwearHouse = wearHouseRepository.findById(id);
        if (optionalwearHouse.isPresent()) {
            return optionalwearHouse.get();
        }
        return new WearHouse();
    }


    @PostMapping
    public Result addWearHouse(@RequestBody WearHouse wearHouse) {
        return wearHouseService.addWearHouse(wearHouse);
    }

    @PutMapping("{id}")
    public Result editWearHouse(@PathVariable Integer id, @RequestBody WearHouse wearHouse) {
        return wearHouseService.editWearHouse(id, wearHouse);
    }

    @DeleteMapping("{id}")
    public Result deleteWearHouse(@PathVariable Integer id) {
        return wearHouseService.deleteWearHouse(id);
    }

}
