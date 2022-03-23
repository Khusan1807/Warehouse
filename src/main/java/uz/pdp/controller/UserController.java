package uz.pdp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.pdp.dto.ApiResponse;
import uz.pdp.dto.UserDto;
import uz.pdp.entity.User;
import uz.pdp.repository.UserRepo;
import uz.pdp.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {


    private final UserRepo userRepo;
    private final UserService userService;

    // Get All Users
    @GetMapping("/all")
    public List<User> getAll() {
        return userRepo.findAll();
    }


    // Get by User id
    @GetMapping("/{id}")
    public ApiResponse getById(@PathVariable Long id) {
        return userService.getById(id);
    }


    @PostMapping("/save")
    public ApiResponse save(@RequestBody UserDto dto) {
        return userService.save(dto);
    }


    @PutMapping("/update/{id}")
    public ApiResponse update(@PathVariable Long id, @RequestBody UserDto dto) {
        return userService.update(id, dto);
    }


    @DeleteMapping("/delete/{id}")
    public ApiResponse delete(@PathVariable Long id) {
        return userService.delete(id);
    }
}
