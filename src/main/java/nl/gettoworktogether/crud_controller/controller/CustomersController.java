package nl.gettoworktogether.crud_controller.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

@RestController
public class CustomersController {

    private static Map<Integer, String> customers = new HashMap<>();
    static {
        customers.put(1, "Jansen");
        customers.put(2, "Pietersen");
        customers.put(3, "Klaassen");
    }

    @RequestMapping(value = "/customers/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> delete(@PathVariable("id") int id) {
        customers.remove(id);
        return new ResponseEntity<>("Product is deleted successfully", HttpStatus.OK);
    }

    @RequestMapping(value = "/customers/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateProduct(@PathVariable("id") int id, @RequestBody String customer) {
        customers.remove(id);
        customers.put(id, customer);
        return new ResponseEntity<>("Customer is updated successfully", HttpStatus.OK);
    }

    @RequestMapping(value = "/customers", method = RequestMethod.POST)
    public ResponseEntity<Object> createCustomer(@RequestBody String customer) {
        Integer maxID = customers.keySet().stream().max(Comparator.comparing(Integer::valueOf)) .get();
        customers.put(maxID + 1, customer);
        return new ResponseEntity<>("Customer is created successfully", HttpStatus.CREATED);
    }

    @RequestMapping(value = "/customers")
    public ResponseEntity<Object> getCustomers() {
        return new ResponseEntity<>(customers.entrySet(), HttpStatus.OK);
    }

    @RequestMapping(value = "/customers/{id}")
    public ResponseEntity<Object> getCustomer(@PathVariable("id") int id) {
        return new ResponseEntity<>(customers.get(id), HttpStatus.OK);
    }

}