package uz.pdp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.pdp.dto.ApiResponse;
import uz.pdp.entity.Measurement;
import uz.pdp.repository.MeasurementRepo;
import uz.pdp.service.MeasurementService;

import java.util.List;

@RestController
@RequestMapping("/measurement")
@RequiredArgsConstructor
public class MeasurementController {


    private final MeasurementService measurementService;
    private final MeasurementRepo measurementRepo;


    @GetMapping("/all")
    public List<Measurement> get() {
        return measurementRepo.findAll();
    }


    @PostMapping("/addOrEdit")
    public ApiResponse addMeasurement(@RequestBody Measurement measurement) {
        return measurementService.addOrEdit(measurement);
    }


    @DeleteMapping("/delete/{id}")
    public ApiResponse delete(@PathVariable Long id) {
        return measurementService.delete(id);
    }

}
