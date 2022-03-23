package uz.pdp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.pdp.dto.ApiResponse;
import uz.pdp.dto.InputDto;
import uz.pdp.entity.Input;
import uz.pdp.repository.InputRepo;
import uz.pdp.service.InputService;

import java.util.List;

@RestController
@RequestMapping("/input")
@RequiredArgsConstructor
public class InputController {


    private final InputService inputService;
    private final InputRepo inputRepo;

    @GetMapping()
    public List<Input> get() {
        return inputRepo.findAll();
    }

    @GetMapping("/{id}")
    public ApiResponse getById(@PathVariable Long id) {
        return inputService.getById(id);
    }


    @PostMapping("/addOrUpdate")
    public ApiResponse addOrUpdate(@RequestBody InputDto dto) {
        return inputService.addOrUpdate(dto);
    }


    @DeleteMapping("/delete/{id}")
    public ApiResponse delete(@PathVariable Long id) {
        return inputService.delete(id);
    }


}
