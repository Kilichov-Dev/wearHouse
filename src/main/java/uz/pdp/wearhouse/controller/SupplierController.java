package uz.pdp.wearhouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.wearhouse.entity.Supplier;
import uz.pdp.wearhouse.payload.Result;
import uz.pdp.wearhouse.repository.SupplierRepository;
import uz.pdp.wearhouse.service.SupplierService;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/supplier")
public class SupplierController {


    @Autowired
    SupplierService supplierService;

    @Autowired
    SupplierRepository supplierRepository;

    @GetMapping
    public List<Supplier> getSuppliers(){
        List<Supplier> supplierList = supplierRepository.findAll();
        return supplierList;
    }

    @GetMapping("{id}")
    public Supplier getSupplier(@PathVariable Integer id){
        Optional<Supplier> optionalSupplier = supplierRepository.findById(id);
        if (optionalSupplier.isPresent()){
            Supplier supplier = optionalSupplier.get();
            return supplier;
        }
        return new Supplier();
    }


    @PostMapping
    public Result addSupplier(@RequestBody Supplier supplier) {
        return supplierService.addSupplier(supplier);
    }

    @PutMapping("{id}")
    public Result editSupplier(@PathVariable Integer id, @RequestBody Supplier supplier) {
        return supplierService.editSupplier(id, supplier);
    }

    @DeleteMapping("{id}")
    public Result deleteSupplier(@PathVariable Integer id) {
        return supplierService.deleteSupplier(id);
    }
}
