package uz.pdp.wearhouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.wearhouse.entity.Users;
import uz.pdp.wearhouse.payload.Result;
import uz.pdp.wearhouse.payload.UserDto;
import uz.pdp.wearhouse.repository.UserRepository;
import uz.pdp.wearhouse.service.UserService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {


    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;

    @GetMapping
    public List<Users> getUsers() {
        List<Users> all = userRepository.findAll();
        return all;
    }

    @GetMapping("/{id}")
    public Users getUsers(@PathVariable Integer id) {
        Optional<Users> optionalUsers = userRepository.findById(id);
        if (optionalUsers.isPresent()) {
            return optionalUsers.get();
        }
        return new Users();
    }

    @PostMapping
    public Result addUsers(@RequestBody UserDto userDto) {
        Result result = userService.addUser(userDto);
        return result;
    }

    @PutMapping("/{id}")
    public Result editUsers(@PathVariable Integer id, @RequestBody UserDto userDto) {
        Result result = userService.editUser(id, userDto);
        return result;
    }

    @DeleteMapping("/{id}")
    public Result deleteUser(@PathVariable Integer id) {
        Result result = userService.deleteUser(id);
        return result;
    }
}
