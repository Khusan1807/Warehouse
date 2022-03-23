package uz.pdp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.dto.ApiResponse;
import uz.pdp.entity.Measurement;
import uz.pdp.repository.MeasurementRepo;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MeasurementService {


    private final MeasurementRepo measurementRepo;

    public ApiResponse addOrEdit(Measurement measurement) {
        try {
            boolean existsByName = measurementRepo.existsByName(measurement.getName());
            Measurement measurement1 = new Measurement();
            if (!existsByName && measurement.getId() != null) {
                measurement1 = measurementRepo.getById(measurement.getId());
            }
            measurement1.setName(measurement.getName());
            measurementRepo.save(measurement1);
            return new ApiResponse(true, measurement.getId() != null ? "Edited!" : "Saved");
        } catch (Exception e) {
            return new ApiResponse(false, "This measurement already exists!");
        }
    }


    public ApiResponse delete(Long id) {
        Optional<Measurement> optionalMeasurement = measurementRepo.findById(id);
        if (!optionalMeasurement.isPresent()) {
            return new ApiResponse(false, "Error on Deleting!");
        }
        measurementRepo.delete(optionalMeasurement.get());
        return new ApiResponse(true, "Deleted!");
    }
}
