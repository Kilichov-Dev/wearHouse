package uz.pdp.wearhouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import uz.pdp.wearhouse.entity.Users;
import uz.pdp.wearhouse.entity.WearHouse;
import uz.pdp.wearhouse.payload.Result;
import uz.pdp.wearhouse.payload.UserDto;
import uz.pdp.wearhouse.repository.UserRepository;
import uz.pdp.wearhouse.repository.WearHouseRepository;

import java.util.*;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    WearHouseRepository wearHouseRepository;

    public Result addUser( UserDto userDto) {
        Users users = new Users();
        users.setFirstName(users.getFirstName());
        users.setLastName(users.getLastName());

        Set<Integer> wearHouse_id = userDto.getWearHouse_id();
        Set<WearHouse> wearHouseSet = new HashSet<>();
        for (Integer integer : wearHouse_id) {
            Optional<WearHouse> optionalWearHouse = wearHouseRepository.findById(integer);
            if (!optionalWearHouse.isPresent()) {
                return new Result("WearHouse not found!", false);
            }
            wearHouseSet.add(optionalWearHouse.get());
        }
        users.setWearHouseSet(wearHouseSet);
//        boolean exists = userRepository.existsByPasswordAndWearHouseSet(userDto.getPassword(),wearHouseSet);
        boolean existss = userRepository.existsByPhoneNumber(userDto.getPhoneNumber());
//        if (exists) {
//            return new Result("Password already exists!", false);
//        }
        if (existss) {
            return new Result("PhoneNumber already exists!", false);
        }
        users.setPassword(userDto.getPassword());
        users.setPhoneNumber(userDto.getPhoneNumber());
        userRepository.save(users);
        return new Result("Users added!", true);
    }

    public Result editUser( Integer id,  UserDto userDto) {
        Optional<Users> optionalUsers = userRepository.findById(id);
        if (optionalUsers.isPresent()) {
            Users users = optionalUsers.get();
            users.setFirstName(users.getFirstName());
            users.setLastName(users.getLastName());

            Set<Integer> wearHouse_id = userDto.getWearHouse_id();
            Set<WearHouse> wearHouseSet = new HashSet<>();
            for (Integer integer : wearHouse_id) {
                Optional<WearHouse> optionalWearHouse = wearHouseRepository.findById(integer);
                if (!optionalWearHouse.isPresent()) {
                    return new Result("WearHouse not found!", false);
                }
                wearHouseSet.add(optionalWearHouse.get());
            }
            users.setWearHouseSet(wearHouseSet);
//            boolean exists = userRepository.existsByPasswordAndWearHouseSet(userDto.getPassword(),wearHouseSet);
            boolean existss = userRepository.existsByPhoneNumber(userDto.getPhoneNumber());
//            if (exists) {
//                return new Result("Password already exists!", false);
//            }
            if (existss) {
                return new Result("PhoneNumber already exists!", false);
            }
            users.setPassword(userDto.getPassword());
            users.setPhoneNumber(userDto.getPhoneNumber());
            userRepository.save(users);
            return new Result("User editing!", true);
        }
        return new Result("User not found!", false);
    }

    public Result deleteUser( Integer id) {
        Optional<Users> optionalUsers = userRepository.findById(id);
        if (optionalUsers.isPresent()) {
            userRepository.deleteById(id);
            return new Result("User deleted!", true);
        }
        return new Result("User not found!", false);
    }
}
