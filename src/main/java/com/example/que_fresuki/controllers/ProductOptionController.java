package com.example.que_fresuki.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.example.que_fresuki.api.ApiResponse;
import com.example.que_fresuki.entitys.models.Product;
import com.example.que_fresuki.entitys.models.ProductOption;
import com.example.que_fresuki.services.ProductOptionService;
import com.example.que_fresuki.utils.Message;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("api/v1")
public class ProductOptionController {

    @Autowired
    private ProductOptionService productOptionService;

    //Get all productsOption
    @GetMapping(path = "/productsOption")
    public List<ProductOption> getProductsOption(){
        return productOptionService.getAllProductOption();
    }

    //Get productOption by ID
    @GetMapping("/productsOption/{id}")
    public ResponseEntity<ProductOption> getProductsOption(@PathVariable Long id){
        return ResponseEntity.ok(productOptionService.getProductOption(id));
    }

    //Update product by id
    @PutMapping("/productsOption/update/{id}")
    public ResponseEntity<ProductOption> putProduct(@PathVariable Long id, @RequestBody ProductOption body){
        return ResponseEntity.ok(productOptionService.updateProductOption(id, body));
    }

    //Add new product
    @PostMapping(path = "/productsOption", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ProductOption> postNewProduct(@RequestBody ProductOption body){
        return ResponseEntity.status(HttpStatus.CREATED).body(productOptionService.saveProductOption(body));
    }

    //Delete product by ID
    @DeleteMapping("/productsOption/{id}")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable Long id){
        ApiResponse api = ApiResponse.builder()
                .code(HttpStatus.NO_CONTENT.value())
                .http(HttpStatus.NO_CONTENT)
                .message(Message.PRODUCT_OPTION_REMOVED_SUCCESSFULLY)
                .build();
                productOptionService.deleteProductOption(id);
        return ResponseEntity.ok(api);
    }
}
