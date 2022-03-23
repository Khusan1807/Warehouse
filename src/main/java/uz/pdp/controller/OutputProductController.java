package uz.pdp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.pdp.dto.ApiResponse;
import uz.pdp.dto.OutputProductDto;
import uz.pdp.entity.OutputProduct;
import uz.pdp.repository.OutputProductRepo;
import uz.pdp.service.OutputProductService;

import java.util.List;

@RestController
@RequestMapping("/outputProduct")
@RequiredArgsConstructor
public class OutputProductController {


    private final OutputProductService outputProductService;
    private final OutputProductRepo outputProductRepo;


    @GetMapping
    public List<OutputProduct> get() {
        return outputProductRepo.findAll();
    }


    @PostMapping("/addOrUpdate")
    public ApiResponse addOrUpdate(@RequestBody OutputProductDto dto) {
        return outputProductService.addOrUpdate(dto);
    }


    @DeleteMapping("/delete/{id}")
    public ApiResponse delete(@PathVariable Long id) {
        return outputProductService.delete(id);
    }


}
