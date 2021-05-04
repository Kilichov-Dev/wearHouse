package uz.pdp.wearhouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.wearhouse.entity.Product;
import uz.pdp.wearhouse.payload.ProductDto;
import uz.pdp.wearhouse.payload.Result;
import uz.pdp.wearhouse.repository.ProductRepository;
import uz.pdp.wearhouse.service.ProductService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;
    @Autowired
    ProductRepository productRepository;

    @GetMapping
    public List<Product> getProducts() {
        List<Product> productList = productRepository.findAll();
        return productList;
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable Integer id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            return optionalProduct.get();
        }
        return new Product();
    }





    @PostMapping
    public Result addProduct(@RequestBody ProductDto productDto) {
        Result result = productService.addProduct(productDto);
        return result;
    }

    @PutMapping("/{id}")
    public Result editProduct(@PathVariable Integer id, @RequestBody ProductDto productDto) {
        Result result = productService.editProduct(id, productDto);
        return result;
    }

    @DeleteMapping("/{id}")
    public Result deleteProduct(@PathVariable Integer id) {
        Result result = productService.deleteProduct(id);
        return result;

    }
}
