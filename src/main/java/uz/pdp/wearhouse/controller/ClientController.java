package uz.pdp.wearhouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.wearhouse.entity.Client;
import uz.pdp.wearhouse.payload.Result;
import uz.pdp.wearhouse.repository.ClientRepository;
import uz.pdp.wearhouse.service.ClientService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/client")
public class ClientController {
    @Autowired
    ClientService clientService;

    @Autowired
    ClientRepository clientRepository;

    @GetMapping
    public List<Client> getClients() {
        List<Client> clientList = clientRepository.findAll();
        return clientList;
    }

    @GetMapping("/{id}")
    public Client getClient(@PathVariable Integer id) {
        Optional<Client> optionalClient = clientRepository.findById(id);
        if (optionalClient.isPresent()) {
            Client client = optionalClient.get();
            return client;
        }
        return new Client();
    }

    @PostMapping
    public Result addClient(@RequestBody Client client) {
        return clientService.addClient(client);
    }

    @PutMapping("{id}")
    public Result editClient(@PathVariable Integer id, @RequestBody Client client) {
        return clientService.editClient(id, client);
    }

    @DeleteMapping("{id}")
    public Result deleteClient(@PathVariable Integer id) {
        return clientService.deleteClient(id);
    }
}
