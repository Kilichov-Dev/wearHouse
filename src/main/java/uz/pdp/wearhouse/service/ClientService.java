package uz.pdp.wearhouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import uz.pdp.wearhouse.entity.Client;
import uz.pdp.wearhouse.payload.Result;
import uz.pdp.wearhouse.repository.ClientRepository;

import java.util.Optional;
@Service
public class ClientService {
    @Autowired
    ClientRepository clientRepository;


    public Result addClient(Client client) {
        boolean exists = clientRepository.existsByPhoneNumber(client.getPhoneNumber());
        if (exists) {
            return new Result("Bunday phone number mavjud!", false);
        }
        clientRepository.save(client);
        return new Result("Successfully", true);
    }


    public Result editClient(Integer id, Client client) {
        Optional<Client> optionalClient = clientRepository.findById(id);
        if (!optionalClient.isPresent()) {
            return new Result("Client not found!", false);
        }

        Client client1 = optionalClient.get();
        boolean exists = clientRepository.existsByPhoneNumber(client.getPhoneNumber());
        if (exists) {
            return new Result("Bunday phone number mavjud!", false);
        }
        client1.setPhoneNumber(client.getPhoneNumber());
        client1.setName(client.getName());
        clientRepository.save(client1);
        return new Result("Client editing!", true);
    }

    public Result deleteClient( Integer id) {
        Optional<Client> optionalClient = clientRepository.findById(id);
        if (optionalClient.isPresent()) {
            clientRepository.deleteById(id);
            return new Result("Client deleted!", true);
        }
        return new Result("Client not found!", false);

    }
}
