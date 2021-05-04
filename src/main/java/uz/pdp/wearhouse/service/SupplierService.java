package uz.pdp.wearhouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import uz.pdp.wearhouse.entity.Supplier;
import uz.pdp.wearhouse.payload.Result;
import uz.pdp.wearhouse.repository.SupplierRepository;

import java.util.Optional;

@Service
public class SupplierService {

    @Autowired
    SupplierRepository supplierRepository;


    public Result addSupplier(Supplier supplier) {
        boolean exists = supplierRepository.existsByPhoneNumber(supplier.getPhoneNumber());
        if (exists) {
            return new Result("Bunday phone number mavjud!", false);
        }
        supplierRepository.save(supplier);
        return new Result("Successfully", true);
    }


    public Result editSupplier( Integer id, Supplier supplier) {
        Optional<Supplier> optionalSupplier = supplierRepository.findById(id);
        if (!optionalSupplier.isPresent()) {
            return new Result("Supplier not found!", false);
        }

        Supplier supplier1 = optionalSupplier.get();
        boolean exists = supplierRepository.existsByPhoneNumber(supplier.getPhoneNumber());
        if (exists) {
            return new Result("Bunday phoneNumber mavjud!", false);
        }

        supplier1.setName(supplier.getName());
        supplier1.setPhoneNumber(supplier.getPhoneNumber());
        supplierRepository.save(supplier1);
        return new Result("Supplier editing!", true);
    }

    public Result deleteSupplier( Integer id) {
        Optional<Supplier> optionalSupplier = supplierRepository.findById(id);
        if (optionalSupplier.isPresent()) {
            supplierRepository.deleteById(id);
            return new Result("Supplier deleted!", true);
        }
        return new Result("Supplier not found!", false);
    }
}
