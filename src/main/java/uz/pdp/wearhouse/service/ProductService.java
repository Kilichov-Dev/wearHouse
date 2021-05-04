package uz.pdp.wearhouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import uz.pdp.wearhouse.entity.Attachments;
import uz.pdp.wearhouse.entity.Category;
import uz.pdp.wearhouse.entity.Measurement;
import uz.pdp.wearhouse.entity.Product;
import uz.pdp.wearhouse.payload.ProductDto;
import uz.pdp.wearhouse.payload.Result;
import uz.pdp.wearhouse.repository.AttachmentRepository;
import uz.pdp.wearhouse.repository.CategoryRepository;
import uz.pdp.wearhouse.repository.MeasurmentRepository;
import uz.pdp.wearhouse.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    MeasurmentRepository measurmentRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    AttachmentRepository attachmentRepository;



    public Result editProduct( Integer id,  ProductDto productDto) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {

            boolean exists = productRepository.existsByNameAndCategoryId(productDto.getName(), productDto.getCategoryId());

            if (exists) {
                return new Result("Bunday mahsulot ushbu kategoriyada mavjud!", false);
            }

            Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());

            if (!optionalCategory.isPresent()) {
                return new Result("Bunday kategoriya mavjud emas!", false);
            }

            Optional<Attachments> optionalAttachments = attachmentRepository.findById(productDto.getPhotoId());

            if (!optionalAttachments.isPresent()) {
                return new Result("Bunday rasm mavjud emas!", false);
            }

            Optional<Measurement> optionalMeasurement = measurmentRepository.findById(productDto.getMeasurementId());

            if (!optionalMeasurement.isPresent()) {
                return new Result("Bunday o'lchov birlik yo'q", false);
            }

            Product product = optionalProduct.get();
            product.setName(product.getName());
            product.setCode("1");
            product.setPhoto(optionalAttachments.get());
            product.setCategory(optionalCategory.get());
            product.setMeasurement(optionalMeasurement.get());
            productRepository.save(product);
            return new Result("Product editing!", true);

        }
        return new Result("Product not found!", false);
    }

    public Result addProduct(ProductDto productDto) {
        boolean exists = productRepository.existsByNameAndCategoryId(productDto.getName(), productDto.getCategoryId());
        if (exists) {
            return new Result("Bunday mahsulot ushbu kategoriyada mavjud!", false);
        }
        Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());
        if (!optionalCategory.isPresent()) {
            return new Result("Bunday kategoriya mavjud emas!", false);

        }
        Optional<Attachments> optionalAttachments = attachmentRepository.findById(productDto.getPhotoId());
        if (!optionalAttachments.isPresent()) {
            return new Result("Bunday rasm mavjud emas!", false);
        }

        Optional<Measurement> optionalMeasurement = measurmentRepository.findById(productDto.getMeasurementId());
        if (!optionalMeasurement.isPresent()) {
            return new Result("Bunday o'lchov birlik mavjud emas!", false);
        }
        Product product = new Product();
        product.setName(product.getName());
        product.setPhoto(optionalAttachments.get());
        product.setCategory(optionalCategory.get());
        product.setMeasurement(optionalMeasurement.get());
        productRepository.save(product);
        return new Result("Product added!", true);

    }

    public Result deleteProduct( Integer id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            productRepository.deleteById(id);
            return new Result("Product deleted!", true);
        }
        return new Result("Product not found!", false);
    }


}
