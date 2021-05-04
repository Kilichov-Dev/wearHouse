package uz.pdp.wearhouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import uz.pdp.wearhouse.entity.Measurement;
import uz.pdp.wearhouse.payload.Result;
import uz.pdp.wearhouse.repository.MeasurmentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MeasurmentService {

    @Autowired
    MeasurmentRepository measurmentRepository;



    public Result addMeasurementService(Measurement measurement) {
        boolean exists = measurmentRepository.existsByName(measurement.getName());
        if (exists) {
            return new Result("Bunday o'lchov birlik mavjud!", false);
        }
        measurmentRepository.save(measurement);
        return new Result("Successfully", true);
    }


    public Result editMeasurement(Integer id, Measurement measurement) {
        Optional<Measurement> optionalMeasurement = measurmentRepository.findById(id);
        Measurement measurement1 = optionalMeasurement.get();
        if (!optionalMeasurement.isPresent()) {
            return new Result("Masurement not found!", false);
        }


        boolean exists = measurmentRepository.existsByName(measurement.getName());
        if (exists) {
            return new Result("Bunday o'lchov birlik mavjud!", false);
        }

        measurement1.setName(measurement.getName());

        measurmentRepository.save(measurement1);
        return new Result("Measurement editing!", true);
    }

    public Result deleteMeasurement(Integer id) {
        Optional<Measurement> optionalMeasurement = measurmentRepository.findById(id);
        if (optionalMeasurement.isPresent()) {
            measurmentRepository.deleteById(id);
            return new Result("Maesuremnt deleted!", true);
        }
        return new Result("Measurement not found!", false);

    }
}
