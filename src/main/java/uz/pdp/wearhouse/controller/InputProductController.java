package uz.pdp.wearhouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.wearhouse.entity.InputProduct;
import uz.pdp.wearhouse.payload.InputProductDto;
import uz.pdp.wearhouse.payload.Result;
import uz.pdp.wearhouse.repository.InputProductRepository;
import uz.pdp.wearhouse.service.InputProductService;

import java.util.List;
import java.util.Optional;

@RequestMapping("/inputProduct")
@RestController

public class InputProductController {
    @Autowired
    InputProductService inputProductService;
    @Autowired
    InputProductRepository inputProductRepository;

    @GetMapping
    public List<InputProduct> getInputProduct() {
        List<InputProduct> all = inputProductRepository.findAll();
        return all;
    }

    @GetMapping("/{id}")
    public InputProduct getInputProduct(@PathVariable Integer id) {
        Optional<InputProduct> optionalInputProduct = inputProductRepository.findById(id);
        if (optionalInputProduct.isPresent()) {
            return optionalInputProduct.get();
        }
        return new InputProduct();
    }

    @PostMapping
    public Result addInputPRoduct(@RequestBody InputProductDto inputProductDto) {
        Result result = inputProductService.addInputProduct(inputProductDto);
        return result;
    }

    @PutMapping
    public Result editInputProduct(@PathVariable Integer id, @RequestBody InputProductDto inputProductDto) {
        Result result = inputProductService.editInputProduct(id, inputProductDto);
        return result;
    }

    @DeleteMapping("/{id}")
    public Result deleteInputProduct(@PathVariable Integer id) {
        Result result = inputProductService.deleteInputProduct(id);
        return result;
    }
}
