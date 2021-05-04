package uz.pdp.wearhouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import uz.pdp.wearhouse.entity.Input;
import uz.pdp.wearhouse.entity.InputProduct;
import uz.pdp.wearhouse.entity.Product;
import uz.pdp.wearhouse.payload.InputProductDto;
import uz.pdp.wearhouse.payload.Result;
import uz.pdp.wearhouse.repository.InputProductRepository;
import uz.pdp.wearhouse.repository.InputRepository;
import uz.pdp.wearhouse.repository.ProductRepository;

import java.util.Optional;

@Service
public class InputProductService {

    @Autowired
    InputProductRepository inputProductRepository;
    @Autowired
    InputRepository inputRepository;
    @Autowired
    ProductRepository productRepository;

    public Result addInputProduct( InputProductDto inputProductDto) {
        InputProduct inputProduct = new InputProduct();
        inputProduct.setAmount(inputProductDto.getAmount());
        inputProduct.setPrice(inputProductDto.getPrice());
        inputProduct.setExpiDate(inputProductDto.getExpiDate());
        Optional<Input> optionalInput = inputRepository.findById(inputProductDto.getInput_id());
        if (!optionalInput.isPresent()) {
            return new Result("Input  not found!", false);
        }
        inputProduct.setInput(optionalInput.get());
        Optional<Product> optionalProduct = productRepository.findById(inputProductDto.getProduct_id());
        if (optionalProduct.isPresent()) {
            return new Result("Product not found!", false);
        }
        inputProduct.setProduct(optionalProduct.get());
        inputProductRepository.save(inputProduct);
        return new Result("Successfully!", true);

    }

    public Result editInputProduct( Integer id,  InputProductDto inputProductDto) {
        Optional<InputProduct> optionalInputProduct = inputProductRepository.findById(id);
        if (optionalInputProduct.isPresent()) {
            InputProduct inputProduct = optionalInputProduct.get();
            inputProduct.setAmount(inputProductDto.getAmount());
            inputProduct.setPrice(inputProductDto.getPrice());
            inputProduct.setExpiDate(inputProductDto.getExpiDate());
            Optional<Input> optionalInput = inputRepository.findById(inputProductDto.getInput_id());
            if (!optionalInput.isPresent()) {
                return new Result("Input  not found!", false);
            }
            inputProduct.setInput(optionalInput.get());
            Optional<Product> optionalProduct = productRepository.findById(inputProductDto.getProduct_id());
            if (optionalProduct.isPresent()) {
                return new Result("Product not found!", false);
            }
            inputProduct.setProduct(optionalProduct.get());
            inputProductRepository.save(inputProduct);
            return new Result("Successfully editing!!", true);
        }
        return new Result("InputProduct not found!", false);

    }

    public Result deleteInputProduct( Integer id) {
        Optional<InputProduct> optionalInputProduct = inputProductRepository.findById(id);
        if (optionalInputProduct.isPresent()) {
            inputProductRepository.deleteById(id);
            return new Result("InputProduct deleted!", true);
        }
        return new Result("InputProduct not found!", false);
    }

}
