package uz.pdp.wearhouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.wearhouse.entity.Output;
import uz.pdp.wearhouse.payload.OutputDto;
import uz.pdp.wearhouse.payload.Result;
import uz.pdp.wearhouse.repository.OutputRepository;
import uz.pdp.wearhouse.service.OutputService;

import javax.persistence.spi.PersistenceUnitTransactionType;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/output")
public class OutputController {

    @Autowired
    OutputService outputService;
    @Autowired
    OutputRepository outputRepository;

    @GetMapping
    public List<Output> getOUtput() {
        List<Output> all = outputRepository.findAll();
        return all;
    }

    @GetMapping("{id}")
    public Output getOutputs(@PathVariable Integer id) {
        Optional<Output> optionalOutput = outputRepository.findById(id);
        if (optionalOutput.isPresent()) return optionalOutput.get();
        return new Output();
    }

    @PostMapping
    public Result addOutput(@RequestBody OutputDto outputDto) {
        Result result = outputService.addOutput(outputDto);
        return result;
    }

    @PutMapping("/{id}")
    public Result editOutput(@PathVariable Integer id, @RequestBody OutputDto outputDto) {
        Result result = outputService.editOutput(id, outputDto);
        return result;
    }

    @DeleteMapping("/{id}")
    public Result deleteOutput(@PathVariable Integer id) {
        Optional<Output> optionalOutput = outputRepository.findById(id);
        if (optionalOutput.isPresent()) {
            outputRepository.deleteById(id);
            return new Result("Output deleted!", true);
        }
        return new Result("Output not found!", false);
    }

}
