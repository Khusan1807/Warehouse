package uz.pdp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.pdp.dto.ApiResponse;
import uz.pdp.entity.Supplier;
import uz.pdp.repository.SupplierRepo;
import uz.pdp.service.SupplierService;

import java.util.List;

@RestController
@RequestMapping("/supplier")
@RequiredArgsConstructor
public class SupplierController {


    private final SupplierService supplierService;
    private final SupplierRepo supplierRepo;


    @GetMapping("/all")
    public List<Supplier> get() {
        return supplierRepo.findAll();
    }


    @PostMapping("/add")
    public ApiResponse add(@RequestBody Supplier supplier) {
        return supplierService.add(supplier);
    }


    @PutMapping("/update/{id}")
    public ApiResponse update(@PathVariable Long id, @RequestBody Supplier supplier) {
        return supplierService.update(id, supplier);
    }


    @DeleteMapping("/delete/{id}")
    public ApiResponse delete(@PathVariable Long id) {
        return supplierService.delete(id);
    }

}
