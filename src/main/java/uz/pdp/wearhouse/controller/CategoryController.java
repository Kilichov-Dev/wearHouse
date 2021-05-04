package uz.pdp.wearhouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.wearhouse.entity.Category;
import uz.pdp.wearhouse.payload.CategoryDto;
import uz.pdp.wearhouse.payload.Result;
import uz.pdp.wearhouse.repository.CategoryRepository;
import uz.pdp.wearhouse.service.CategoryService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping
    public List<Category> getCategories() {
        List<Category> all = categoryRepository.findAll();
        return all;

    }

    @GetMapping("/{id}")
    public Category getCategory(@PathVariable Integer id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isPresent()) {
            return optionalCategory.get();
        }
        return new Category();

    }

    @PostMapping
    public Result addCategory(@RequestBody CategoryDto categoryDto) {
        Result result = categoryService.addCategory(categoryDto);
        return result;
    }

    @PutMapping("/{id}")
    public Result editCategory(@PathVariable Integer id, @RequestBody CategoryDto categoryDto) {
        Result result = categoryService.editCategory(id, categoryDto);
        return result;
    }

    @DeleteMapping("/{id}")
    public Result deleteCategory(@PathVariable Integer id) {
        Result result = categoryService.deleteCategory(id);
        return result;
    }


}
