package uz.pdp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.dto.ApiResponse;
import uz.pdp.dto.ProductDto;
import uz.pdp.entity.Category;
import uz.pdp.entity.Measurement;
import uz.pdp.entity.Product;
import uz.pdp.entity.attachment.Attachment;
import uz.pdp.repository.AttachmentRepo;
import uz.pdp.repository.CategoryRepo;
import uz.pdp.repository.MeasurementRepo;
import uz.pdp.repository.ProductRepo;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {


    private final ProductRepo productRepo;
    private final CategoryRepo categoryRepo;
    private final AttachmentRepo attachmentRepo;
    private final MeasurementRepo measurementRepo;

    public ApiResponse getById(Long id) {
        Optional<Product> optionalProduct = productRepo.findById(id);
        if (optionalProduct.isPresent()) {
            return new ApiResponse(true, "Success", optionalProduct.get());
        }
        return new ApiResponse(false, "Product not Found");
    }


    public ApiResponse addOrUpdate(ProductDto dto) {
        // Agar shunaqa maxsulot bo'lmasa yangi product yaratib olamiz:
        Product product = new Product();
        if (dto.getId() != null) {
            product = productRepo.getById(dto.getId());
        } else if (productRepo.existsByNameAndCategoryId(dto.getName(), dto.getCategoryId())) {
            return new ApiResponse(false, "This kind of product already exists in this category!");
        }

        Optional<Category> optionalCategory = categoryRepo.findById(dto.getCategoryId());
        if (!optionalCategory.isPresent()) {
            return new ApiResponse(false, "Cannot find Category!");
        }
        Optional<Attachment> optionalAttachment = attachmentRepo.findById(dto.getPhotoId());
        if (!optionalAttachment.isPresent()) {
            return new ApiResponse(false, "Cannot find Photo");
        }
        Optional<Measurement> optionalMeasurement = measurementRepo.findById(dto.getMeasurementId());
        if (!optionalMeasurement.isPresent()) {
            return new ApiResponse(false, "Cannot find Measurement");
        }

        product.setName(dto.getName());
        product.setCategory(optionalCategory.get());
        product.setPhoto(optionalAttachment.get());
        product.setMeasurement(optionalMeasurement.get());
        productRepo.save(product);
        return new ApiResponse(true, dto.getId() != null ? "Edited" : "Saved");
    }


    public ApiResponse delete(Long id) {
        Optional<Product> optionalProduct = productRepo.findById(id);
        if (!optionalProduct.isPresent()) {
            return new ApiResponse(false, "Error on Deleting!");
        }
        productRepo.delete(optionalProduct.get());
        return new ApiResponse(true, "Deleted!");

    }


}
