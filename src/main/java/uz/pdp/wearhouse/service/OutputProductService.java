package uz.pdp.wearhouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import uz.pdp.wearhouse.entity.Output;
import uz.pdp.wearhouse.entity.OutputProduct;
import uz.pdp.wearhouse.entity.Product;
import uz.pdp.wearhouse.payload.OutputProductDto;
import uz.pdp.wearhouse.payload.Result;
import uz.pdp.wearhouse.repository.OutputProductRepository;
import uz.pdp.wearhouse.repository.OutputRepository;
import uz.pdp.wearhouse.repository.ProductRepository;

import java.util.Optional;

@Service
public class OutputProductService {


    @Autowired
    OutputProductRepository outputProductRepository;
    @Autowired
    OutputRepository outputRepository;
    @Autowired
    ProductRepository productRepository;

    public Result addOutputProduct( OutputProductDto outputProductDto) {
        OutputProduct outputProduct = new OutputProduct();
        outputProduct.setAmount(outputProductDto.getAmount());
        outputProduct.setPrice(outputProductDto.getPrice());
        Optional<Output> optionalOutput = outputRepository.findById(outputProductDto.getOutput_id());
        if (!optionalOutput.isPresent()) return new Result("Output no found!", false);
        outputProduct.setOutput(optionalOutput.get());
        Optional<Product> optionalProduct = productRepository.findById(outputProductDto.getProduct_id());
        if (!optionalProduct.isPresent()) return new Result("Product not found!", false);
        outputProduct.setProduct(optionalProduct.get());
        outputProductRepository.save(outputProduct);
        return new Result("Output added successfully!", true);
    }

    public Result editOutputProduct( Integer id,  OutputProductDto outputProductDto) {
        Optional<OutputProduct> optionalOutputProduct = outputProductRepository.findById(id);
        if (optionalOutputProduct.isPresent()) {
            OutputProduct outputProduct = optionalOutputProduct.get();
            outputProduct.setAmount(outputProductDto.getAmount());
            outputProduct.setPrice(outputProductDto.getPrice());
            Optional<Output> optionalOutput = outputRepository.findById(outputProductDto.getOutput_id());
            if (!optionalOutput.isPresent()) return new Result("Output no found!", false);
            outputProduct.setOutput(optionalOutput.get());
            Optional<Product> optionalProduct = productRepository.findById(outputProductDto.getProduct_id());
            if (!optionalProduct.isPresent()) return new Result("Product not found!", false);
            outputProduct.setProduct(optionalProduct.get());
            outputProductRepository.save(outputProduct);
            return new Result("OutputProduct editing successfully!", true);

        }
        return new Result("OutputProduct not found!", false);
    }

    public Result deleteOutputProduct( Integer id) {
        Optional<OutputProduct> optionalOutputProduct = outputProductRepository.findById(id);
        if (optionalOutputProduct.isPresent()) {
            outputProductRepository.deleteById(id);
            return new Result("OutputProduct deleted!", true);
        }
        return new Result("OutputProduct not found!", false);
    }
}
