package uz.pdp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.pdp.dto.ApiResponse;
import uz.pdp.dto.OutputDto;
import uz.pdp.entity.Output;
import uz.pdp.repository.OutputRepo;
import uz.pdp.service.OutputService;

import java.util.List;

@RestController
@RequestMapping("/output")
@RequiredArgsConstructor
public class OutputController {


    private final OutputService outputService;
    private final OutputRepo outputRepo;

    @GetMapping()
    public List<Output> get() {
        return outputRepo.findAll();
    }

    @GetMapping("/{id}")
    public ApiResponse getById(@PathVariable Long id) {
        return outputService.getById(id);
    }


    @PostMapping("/addOrUpdate")
    public ApiResponse addOrUpdate(@RequestBody OutputDto dto) {
        return outputService.addOrUpdate(dto);
    }


    @DeleteMapping("/delete/{id}")
    public ApiResponse delete(@PathVariable Long id) {
        return outputService.delete(id);
    }


}
