package uz.pdp.wearhouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import uz.pdp.wearhouse.entity.Category;
import uz.pdp.wearhouse.payload.CategoryDto;
import uz.pdp.wearhouse.payload.Result;
import uz.pdp.wearhouse.repository.CategoryRepository;

import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    CategoryService categoryService;
    @Autowired
    CategoryRepository categoryRepository;


    public Result addCategory(CategoryDto categoryDto) {
        Category category = new Category();
        boolean exists = categoryRepository.existsByName(categoryDto.getName());
        if (exists) return new Result("This category name already exists!", false);
        category.setName(categoryDto.getName());
        if (categoryDto.getParentCategoryId() != null) {
            Optional<Category> optionalCategory = categoryRepository.findById(categoryDto.getParentCategoryId());
            if (!optionalCategory.isPresent()) {
                return new Result("Bunday kategorya mavjud emas!", false);
            }
            category.setParentCategory(optionalCategory.get());
        }
        categoryRepository.save(category);
        return new Result("Muvaffiqayatli saqlandi!", true);
    }

    public Result editCategory( Integer id, CategoryDto categoryDto) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isPresent()) {
            Category category = optionalCategory.get();
            boolean exists = categoryRepository.existsByName(categoryDto.getName());
            if (exists) return new Result("This category name already exists!", false);
            category.setName(categoryDto.getName());
            if (categoryDto.getParentCategoryId() != null) {
                Optional<Category> optionalCategory1 = categoryRepository.findById(id);
                if (!optionalCategory1.isPresent()) {
                    return new Result("Bunday parent kategory mavjud emas!", false);
                }
                category.setParentCategory(optionalCategory1.get());
            }
            categoryRepository.save(category);
            return new Result("Category editing!", true);
        }
        return new Result("Kategory topilmadi!", false);
    }

    public Result deleteCategory( Integer id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isPresent()) {
            categoryRepository.deleteById(id);
            return new Result("Category deleted!", true);
        }
        return new Result("Category not found!", false);
    }
}
