package com.example.que_fresuki.controllers;

import com.example.que_fresuki.api.ApiResponse;
import com.example.que_fresuki.entitys.models.RawMaterial;
import com.example.que_fresuki.services.RawMaterialService;
import com.example.que_fresuki.utils.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class RawMaterialController {

    @Autowired
    private RawMaterialService service;

    @GetMapping(path = "/rawMaterials")
    public List<RawMaterial> getRawMaterials() {
        return service.getRawMaterials();
    }

    @GetMapping("/rawMaterials/{id}")
    public ResponseEntity<RawMaterial> getRawMaterial(@PathVariable Long id) {
        return ResponseEntity.ok(service.getRawMaterial(id));
    }

    @PutMapping("/rawMaterials/update/{id}")
    public ResponseEntity<RawMaterial> putRawMaterial(@PathVariable Long id, @RequestBody RawMaterial body) {
        return ResponseEntity.ok(service.updateRawMaterial(id, body));
    }

    @PostMapping(path = "/rawMaterials")
    public ResponseEntity<RawMaterial> postNewRawMaterial(@RequestBody RawMaterial body) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.saveRawMaterial(body));
    }

    @DeleteMapping("/rawMaterials/{id}")
    public ResponseEntity<ApiResponse> deleteRawMaterial(@PathVariable Long id) {
        ApiResponse api = ApiResponse.builder()
                .code(HttpStatus.NO_CONTENT.value())
                .http(HttpStatus.NO_CONTENT)
                .message(Message.RAW_MATERIAL_REMOVED_SUCCESSFULLY)
                .build();
        service.deleteRawMaterial(id);
        return ResponseEntity.ok(api);
    }
}
