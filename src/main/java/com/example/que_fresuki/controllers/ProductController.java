package com.example.que_fresuki.controllers;

import com.example.que_fresuki.api.ApiResponse;
import com.example.que_fresuki.entitys.models.Product;
import com.example.que_fresuki.services.ProductService;
import com.example.que_fresuki.utils.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ProductController {

    /*private final ProductService service;
    @Autowired
    public ProductController(ProductService service) {
        this.service = service;
    }*/

    @Autowired
    private ProductService service;


    //Get all products
    @GetMapping(path = "/products")
    public List<Product> getProducts(){
        return service.getAllProduct();
    }

    //Get product by ID
    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id){
        return ResponseEntity.ok(service.getProduct(id));
    }

    //Update product by id
    @PutMapping("/products/update/{id}")
    public ResponseEntity<Product> putProduct(@PathVariable Long id, @RequestBody Product body){
        return ResponseEntity.ok(service.updateProduct(id, body));
    }

    //Add new product
    @PostMapping(path = "/products")
    public ResponseEntity<Product> postNewProduct(@RequestBody Product body){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.saveProduct(body));
    }

    //Delete product by ID
    @DeleteMapping("/products/{id}")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable Long id){
        ApiResponse api = ApiResponse.builder()
                .code(HttpStatus.NO_CONTENT.value())
                .http(HttpStatus.NO_CONTENT)
                .message(Message.PRODUCT_REMOVED_SUCCESSFULLY)
                .build();
        service.deleteProduct(id);
        return ResponseEntity.ok(api);
    }
}
