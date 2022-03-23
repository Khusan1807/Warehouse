package uz.pdp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.dto.ApiResponse;
import uz.pdp.dto.CategoryDto;
import uz.pdp.entity.Category;
import uz.pdp.repository.CategoryRepo;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {


    private final CategoryRepo categoryRepo;


    public ApiResponse addCategory(CategoryDto dto) {
        Category category = new Category();
        category.setName(dto.getName());
        if (dto.getParentCategoryId() != null) {
            Optional<Category> parentCategoryId = categoryRepo.findById(dto.getParentCategoryId());
            if (!parentCategoryId.isPresent())
                return new ApiResponse(false, "Parent Category not found!");
            category.setParentCategoryId(parentCategoryId.get());
        }
        categoryRepo.save(category);
        return new ApiResponse(true, "Saved");
    }


    public ApiResponse update(Long id, CategoryDto dto) {
        Optional<Category> optionalCategory = categoryRepo.findById(id);
        if (optionalCategory.isPresent()) {
            Category category = optionalCategory.get();
            category.setName(dto.getName());
            Optional<Category> parentCategory = categoryRepo.findById(dto.getParentCategoryId());
            if (parentCategory.isPresent()) {
                category.setParentCategoryId(parentCategory.get());
            }
            categoryRepo.save(category);
            return new ApiResponse(true, "Updated!");
        }
        return new ApiResponse(false, "Category Not Found!");
    }


    public ApiResponse delete(Long id) {
        try {
            categoryRepo.deleteById(id);
            return new ApiResponse(true, "Deleted!");
        } catch (Exception e) {
            return new ApiResponse(false, "Error on Deleting!");
        }
    }
}
