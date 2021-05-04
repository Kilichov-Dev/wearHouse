package uz.pdp.wearhouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.wearhouse.entity.OutputProduct;
import uz.pdp.wearhouse.payload.OutputProductDto;
import uz.pdp.wearhouse.payload.Result;
import uz.pdp.wearhouse.repository.OutputProductRepository;
import uz.pdp.wearhouse.service.OutputProductService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/outputProduct")
public class OutputProductController {

    @Autowired
    OutputProductService outputProductService;

    @Autowired
    OutputProductRepository outputProductRepository;

    @GetMapping
    public List<OutputProduct> getOutputProduct() {
        List<OutputProduct> all = outputProductRepository.findAll();
        return all;
    }

    @GetMapping("/{id}")
    public OutputProduct getOutputProduct(@PathVariable Integer id) {
        Optional<OutputProduct> optionalOutputProduct = outputProductRepository.findById(id);
        if (optionalOutputProduct.isPresent()) {
            return optionalOutputProduct.get();
        }
        return new OutputProduct();
    }

    @PostMapping
    public Result addOutputProduct(@RequestBody OutputProductDto outputProductDto) {
        Result result = outputProductService.addOutputProduct(outputProductDto);
        return result;
    }

    @PutMapping("/{id}")
    public Result editOutputProduct(@PathVariable Integer id, @RequestBody OutputProductDto outputProductDto) {
        Result result = outputProductService.editOutputProduct(id, outputProductDto);
        return result;
    }

    @DeleteMapping("/{id}")
    public Result deleteOutputProduct(@PathVariable Integer id) {
        Result result = outputProductService.deleteOutputProduct(id);
        return result;
    }

}
