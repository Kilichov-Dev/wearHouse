package uz.pdp.wearhouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.wearhouse.entity.Input;
import uz.pdp.wearhouse.payload.InputDto;
import uz.pdp.wearhouse.payload.Result;
import uz.pdp.wearhouse.repository.InputRepository;
import uz.pdp.wearhouse.service.InputService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/input")
public class InputController {
    @Autowired
    InputService inputService;
    @Autowired
    InputRepository inputRepository;

    @GetMapping
    public List<Input> getInput() {
        List<Input> all = inputRepository.findAll();
        return all;
    }


    @GetMapping("/{id}")
    public Input getInput(@PathVariable Integer id) {
        Optional<Input> optionalInput = inputRepository.findById(id);
        if (optionalInput.isPresent()) {
            return optionalInput.get();
        }
        return new Input();
    }

    @PostMapping
    public Result addInput(@RequestBody InputDto inputDto) {
        Result result = inputService.addInput(inputDto);
        return result;
    }

    @PutMapping("/{id}")
    public Result editInput(@PathVariable Integer id, @RequestBody InputDto inputDto) {
        Result result = inputService.editInput(id, inputDto);
        return result;
    }

    @DeleteMapping("/{id}")
    public Result deleteInput(@PathVariable Integer id) {
        Result result = inputService.deleteInput(id);
        return result;
    }
}
