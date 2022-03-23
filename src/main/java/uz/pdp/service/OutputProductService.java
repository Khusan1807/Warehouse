package uz.pdp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.dto.ApiResponse;
import uz.pdp.dto.OutputProductDto;
import uz.pdp.entity.Output;
import uz.pdp.entity.OutputProduct;
import uz.pdp.entity.Product;
import uz.pdp.repository.OutputProductRepo;
import uz.pdp.repository.OutputRepo;
import uz.pdp.repository.ProductRepo;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OutputProductService {


    private final OutputProductRepo outputProductRepo;
    private final ProductRepo productRepo;
    private final OutputRepo outputRepo;


    public ApiResponse addOrUpdate(OutputProductDto dto) {
        OutputProduct outputProduct = new OutputProduct();
        if (dto.getId() != null) {
            outputProduct = outputProductRepo.getById(dto.getId());
        }
        Optional<Product> optionalProduct = productRepo.findById(dto.getProductId());
        if (!optionalProduct.isPresent()) {
            return new ApiResponse(false, "Product Not Found!");
        }
        Optional<Output> optionalOutput = outputRepo.findById(dto.getOutputId());
        if (!optionalOutput.isPresent()) {
            return new ApiResponse(false, "Output not Found!");
        }

        outputProduct.setOutput(optionalOutput.get());
        outputProduct.setProduct(optionalProduct.get());
        outputProduct.setAmount(dto.getAmount());
        outputProduct.setPrice(dto.getPrice());
        outputProductRepo.save(outputProduct);
        return new ApiResponse(true, dto.getId() != null ? "Edited" : "Saved");
    }


    public ApiResponse delete(Long id) {
        try {
            outputProductRepo.deleteById(id);
            return new ApiResponse(true, "Deleted!");
        } catch (Exception e) {
            return new ApiResponse(false, "Error on Deleting!");
        }
    }


}
