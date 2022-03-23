package uz.pdp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.pdp.dto.ApiResponse;
import uz.pdp.dto.InputProductDto;
import uz.pdp.entity.InputProduct;
import uz.pdp.repository.InputProductRepo;
import uz.pdp.service.InputProductService;

import java.util.List;

@RestController
@RequestMapping("/inputProduct")
@RequiredArgsConstructor
public class InputProductController {


    private final InputProductService inputProductService;
    private final InputProductRepo inputProductRepo;


    @GetMapping
    public List<InputProduct> get() {
        return inputProductRepo.findAll();
    }


    @PostMapping("/addOrUpdate")
    public ApiResponse addOrUpdate(@RequestBody InputProductDto dto) {
        return inputProductService.addOrUpdate(dto);
    }


    @DeleteMapping("/delete/{id}")
    public ApiResponse delete(@PathVariable Long id) {
        return inputProductService.delete(id);
    }


}
