package uz.pdp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.dto.ApiResponse;
import uz.pdp.dto.UserDto;
import uz.pdp.entity.User;
import uz.pdp.entity.Warehouse;
import uz.pdp.repository.UserRepo;
import uz.pdp.repository.WarehouseRepo;

import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {


    private final UserRepo userRepo;
    private final WarehouseRepo warehouseRepo;


    public ApiResponse getById(Long id) {
        Optional<User> optionalUser = userRepo.findById(id);
        if (optionalUser.isPresent()) {
            return new ApiResponse(true, "Success", optionalUser.get());
        }
        return new ApiResponse(false, "User not Found");
    }

    public ApiResponse save(UserDto dto) {
        boolean existsByPhoneNumber = userRepo.existsByPhoneNumber(dto.getPhoneNumber());
        if (existsByPhoneNumber) {
            return new ApiResponse(false, "This phoneNumber already exists");
        }
        Optional<Warehouse> optionalWarehouse = warehouseRepo.findById(dto.getWarehouseId());
        if (!optionalWarehouse.isPresent()) {
            return new ApiResponse(false, "Warehouse not found!");
        }

        boolean existsByCode = userRepo.existsByCode(dto.getCode());
        if (existsByCode) {
            return new ApiResponse(false, "Such code already exists!");
        }

        Set<Warehouse> warehouseSet = new LinkedHashSet<>();
        warehouseSet.add(optionalWarehouse.get());

        User user = new User(dto.getFirstName(), dto.getLastName(), dto.getPhoneNumber(),
                dto.getCode(), dto.getPassword(), warehouseSet);
        userRepo.save(user);
        return new ApiResponse(true, "Success", user);
    }


    public ApiResponse update(Long id, UserDto dto) {
        Optional<User> optionalUser = userRepo.findById(id);
        if (!optionalUser.isPresent()) {
            return new ApiResponse(false, "User not found!");
        }
        User user = optionalUser.get();

        Optional<Warehouse> optionalWarehouse = warehouseRepo.findById(dto.getWarehouseId());
        if (!optionalWarehouse.isPresent()) {
            return new ApiResponse(false, "Warehouse not found!");
        }
        Set<Warehouse> warehouseSet = new LinkedHashSet<>();
        warehouseSet.add(optionalWarehouse.get());

        for (User forEachUser : userRepo.findAll()) {
            if (!forEachUser.getId().equals(user.getId()) && forEachUser.getPhoneNumber().equals(dto.getPhoneNumber())) {
                return new ApiResponse(false, "This phone number already exists!");
            }
            if (!forEachUser.getId().equals(user.getId()) && forEachUser.getCode().equals(dto.getCode())) {
                return new ApiResponse(false, "This code already exists!");
            }
        }

        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setPhoneNumber(dto.getPhoneNumber());
        user.setCode(dto.getCode());
        user.setPassword(dto.getPassword());
        user.setActive(dto.isActive());
        user.setWarehouses(warehouseSet);
        userRepo.save(user);
        return new ApiResponse(true, "Success", user);
    }


    public ApiResponse delete(Long id) {
        Optional<User> optionalUser = userRepo.findById(id);
        if (!optionalUser.isPresent()) {
            return new ApiResponse(false, "Error on Deleting!");
        }
        userRepo.delete(optionalUser.get());
        return new ApiResponse(true, "Deleted!");

    }
}
