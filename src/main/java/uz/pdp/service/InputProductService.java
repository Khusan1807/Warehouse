package uz.pdp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.dto.ApiResponse;
import uz.pdp.dto.InputProductDto;
import uz.pdp.entity.Input;
import uz.pdp.entity.InputProduct;
import uz.pdp.entity.Product;
import uz.pdp.repository.InputProductRepo;
import uz.pdp.repository.InputRepo;
import uz.pdp.repository.ProductRepo;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InputProductService {


    private final InputProductRepo inputProductRepo;
    private final ProductRepo productRepo;
    private final InputRepo inputRepo;


    public ApiResponse addOrUpdate(InputProductDto dto) {
        InputProduct inputProduct = new InputProduct();
        if (dto.getId() != null) {
            inputProduct = inputProductRepo.getById(dto.getId());
        }
        Optional<Product> optionalProduct = productRepo.findById(dto.getProductId());
        if (!optionalProduct.isPresent()) {
            return new ApiResponse(false, "Product Not Found!");
        }
        Optional<Input> optionalInput = inputRepo.findById(dto.getInputId());
        if (!optionalInput.isPresent()) {
            return new ApiResponse(false, "Input noty Found!");
        }

        inputProduct.setInput(optionalInput.get());
        inputProduct.setProduct(optionalProduct.get());
        inputProduct.setAmount(dto.getAmount());
        inputProduct.setPrice(dto.getPrice());
        inputProduct.setExpireDate(dto.getExpireDate());
        inputProductRepo.save(inputProduct);
        return new ApiResponse(true, dto.getId() != null ? "Edited" : "Saved");
    }


    public ApiResponse delete(Long id) {
        try {
            inputProductRepo.deleteById(id);
            return new ApiResponse(true, "Deleted!");
        } catch (Exception e) {
            return new ApiResponse(false, "Error on Deleting!");
        }
    }


}
