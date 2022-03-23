package uz.pdp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.pdp.dto.ApiResponse;
import uz.pdp.dto.ProductDto;
import uz.pdp.entity.Product;
import uz.pdp.repository.ProductRepo;
import uz.pdp.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {


    private final ProductService productService;
    private final ProductRepo productRepo;

    @GetMapping()
    public List<Product> get() {
        return productRepo.findAll();
    }

    @GetMapping("/{id}")
    public ApiResponse getById(@PathVariable Long id) {
        return productService.getById(id);
    }


    @PostMapping("/addOrUpdate")
    public ApiResponse addProduct(@RequestBody ProductDto dto) {
        return productService.addOrUpdate(dto);
    }


    @DeleteMapping("/delete/{id}")
    public ApiResponse delete(@PathVariable Long id) {
        return productService.delete(id);
    }


}
