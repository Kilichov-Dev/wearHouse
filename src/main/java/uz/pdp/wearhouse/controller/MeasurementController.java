package uz.pdp.wearhouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.wearhouse.entity.Measurement;
import uz.pdp.wearhouse.payload.Result;
import uz.pdp.wearhouse.repository.MeasurmentRepository;
import uz.pdp.wearhouse.service.MeasurmentService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/measurement")
public class MeasurementController {

    @Autowired
    MeasurmentService measurmentService;
    @Autowired
    MeasurmentRepository measurmentRepository;

    @GetMapping
    public List<Measurement> getMeasurements() {
        List<Measurement> measurementList = measurmentRepository.findAll();
        return measurementList;
    }

    @GetMapping("/{id}")
    public Measurement getMeasurement(@PathVariable Integer id) {
        Optional<Measurement> optionalMeasurement = measurmentRepository.findById(id);
        if (optionalMeasurement.isPresent()) {
            return optionalMeasurement.get();
        }
        return new Measurement();
    }


    @PostMapping
    public Result addMeasurement(@RequestBody Measurement measurement) {
        return measurmentService.addMeasurementService(measurement);
    }

    @PutMapping("/{id}")
    public Result editMeasurement(@PathVariable Integer id,@RequestBody Measurement measurement) {
        return measurmentService.editMeasurement(id, measurement);
    }

    @DeleteMapping("/{id}")
    public Result deleteMeasuremnt(@PathVariable Integer id) {
        return measurmentService.deleteMeasurement(id);
    }
}
