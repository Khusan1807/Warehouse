package uz.pdp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.pdp.dto.ApiResponse;
import uz.pdp.dto.CategoryDto;
import uz.pdp.entity.Category;
import uz.pdp.repository.CategoryRepo;
import uz.pdp.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {


    private final CategoryService categoryService;
    private final CategoryRepo categoryRepo;


    @GetMapping
    public List<Category> getAllCategories() {
        return categoryRepo.findAll();
    }


    @PostMapping("/add")
    public ApiResponse addCategory(@RequestBody CategoryDto dto) {
        return categoryService.addCategory(dto);
    }


    @PutMapping("/edit/{id}")
    public ApiResponse update(@PathVariable Long id, @RequestBody CategoryDto dto) {
        return categoryService.update(id, dto);
    }


    @DeleteMapping("/delete/{id}")
    public ApiResponse delete(@PathVariable Long id) {
        return categoryService.delete(id);
    }
}
