package uz.pdp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.dto.ApiResponse;
import uz.pdp.entity.Supplier;
import uz.pdp.repository.SupplierRepo;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SupplierService {


    private final SupplierRepo supplierRepo;

    public ApiResponse add(Supplier supplier) {
        try {
            Supplier supplier1 = new Supplier();
            boolean existsByPhoneNumber = supplierRepo.existsByPhoneNumber(supplier.getPhoneNumber());
            if (existsByPhoneNumber) {
                return new ApiResponse(false, "This phone number already exists!");
            }
            supplier1.setName(supplier.getName());
            supplier1.setPhoneNumber(supplier.getPhoneNumber());
            supplierRepo.save(supplier1);
            return new ApiResponse(true, "Saved");
        } catch (Exception e) {
            return new ApiResponse(false, "Error on Saving!");
        }
    }


    public ApiResponse delete(Long id) {
        Optional<Supplier> optionalSupplier = supplierRepo.findById(id);
        if (!optionalSupplier.isPresent()) {
            return new ApiResponse(false, "Error on Deleting!");
        }
        supplierRepo.delete(optionalSupplier.get());
        return new ApiResponse(true, "Deleted!");
    }

    public ApiResponse update(Long id, Supplier supplier) {
        boolean check = false;
        Optional<Supplier> optionalSupplier = supplierRepo.findById(id);
        if (optionalSupplier.isPresent()) {
            Supplier optionalSup = optionalSupplier.get();
            optionalSup.setName(supplier.getName());

            for (Supplier forEachSupplier : supplierRepo.findAll()) {
                if (forEachSupplier.getId() != supplier.getId() && forEachSupplier.getPhoneNumber().equals(supplier.getPhoneNumber())) {
                    check = true;
                    break;
                }
            }
            if (check) {
                return new ApiResponse(false, "This phone number already exists!");
            }
            optionalSup.setPhoneNumber(supplier.getPhoneNumber());
            supplierRepo.save(optionalSup);
            return new ApiResponse(true, "Updated");
        }
        return new ApiResponse(false, "Supplier not found!");
    }

}
