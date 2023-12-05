package com.example.que_fresuki.controllers;

import com.example.que_fresuki.api.ApiResponse;
import com.example.que_fresuki.entitys.models.Ingredient;
import com.example.que_fresuki.services.IngredientService;
import com.example.que_fresuki.utils.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class IngredientController {

    @Autowired
    private IngredientService service;


    /*public IngredientController(IngredientService service){
        this.service = service;
    }*/

    @GetMapping(path = "/ingredients")
    public List<Ingredient> getProducts(){
        return service.getIngredients();
    }

    //Get ingredient by ID
    @GetMapping("/ingredients/{id}")
    public ResponseEntity<Ingredient> getIngredient(@PathVariable Long id){
        return ResponseEntity.ok(service.getIngredient(id));
    }

    //Update ingredient by id
    @PutMapping("/ingredients/update/{id}")
    public ResponseEntity<Ingredient> putIngredient(@PathVariable Long id, @RequestBody Ingredient body){
        return ResponseEntity.ok(service.updateIngredient(id, body));
    }

    //Add new Ingredient
    @PostMapping(path = "/ingredients")
    public ResponseEntity<Ingredient> postNewIngredient(@RequestBody Ingredient body){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.saveIngredient(body));
    }

    //Delete Ingredient by ID
    @DeleteMapping("/ingredients/{id}")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable Long id){
        ApiResponse api = ApiResponse.builder()
                .code(HttpStatus.NO_CONTENT.value())
                .http(HttpStatus.NO_CONTENT)
                .message(Message.INGREDIENT_REMOVED_SUCCESSFULLY)
                .build();
        service.deleteIngredient(id);
        return ResponseEntity.ok(api);
    }
}
