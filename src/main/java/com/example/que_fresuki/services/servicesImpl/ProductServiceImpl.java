package com.example.que_fresuki.services.servicesImpl;

import com.example.que_fresuki.entitys.models.Product;
import com.example.que_fresuki.entitys.models.ProductRawMaterial;
import com.example.que_fresuki.entitys.models.RawMaterial;
import com.example.que_fresuki.exceptions.InvalidProductExceptions;
import com.example.que_fresuki.exceptions.NotFoundProductExceptions;
import com.example.que_fresuki.exceptions.rawMaterialExceptions.NotFoundRawMaterialExceptions;
import com.example.que_fresuki.repositorys.IProductRawMaterialRepository;
import com.example.que_fresuki.repositorys.IProductRepository;
import com.example.que_fresuki.repositorys.IRawMaterialRepository;
import com.example.que_fresuki.services.ProductService;
import com.example.que_fresuki.utils.Message;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Log4j2
public class ProductServiceImpl implements ProductService {

    private final IProductRepository repository;
    private final IRawMaterialRepository repositoryRawMaterial;
    private final IProductRawMaterialRepository repositoryProductRawMaterial;

    @Autowired
    public ProductServiceImpl(IProductRepository repository, IRawMaterialRepository repositoryRawMaterial, IProductRawMaterialRepository repositoryProductRawMaterial){
        this.repository = repository;
        this.repositoryRawMaterial = repositoryRawMaterial;
        this.repositoryProductRawMaterial = repositoryProductRawMaterial;
    }


    @Transactional(readOnly = true)
    //@Override
    public List<Product> getAllProduct() {
        log.info(" LISTADO DE PRODUCTO ");
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    //@Override
    public Product getProduct(@NonNull Long id) {
        log.info(" PRODUCTO ");
        return repository.findById(id)
                        .orElseThrow( () ->
                        new NotFoundProductExceptions(Message.NOT_FOUND_PRODUCT, HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND)
                );
    }

    @Transactional
    public Product saveProduct(Product productBody) {

        Optional<Product> productDB = repository.findByNameIgnoreCase(productBody.getName());
        if(productDB.isPresent()){
            throw new InvalidProductExceptions(Message.PRODUCT_ALREADY_EXISTS,HttpStatus.CONFLICT.value(),HttpStatus.CONFLICT);
        }

        // Itera sobre cada ProductRawMaterial en la lista de productRawMaterials del producto proporcionado.
        for(ProductRawMaterial productRawMaterial : productBody.getProductRawMaterials()){
            // Establece el producto de este ProductRawMaterial como el producto proporcionado.
            productRawMaterial.setProduct(productBody);

            Long rawMaterialId = productRawMaterial.getRawMaterial().getId();
            if (rawMaterialId == null) {
                throw new NotFoundRawMaterialExceptions(Message.NOT_FOUND_RAW_MATERIAL, HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND);
            }

            // Busca en la base de datos la materia prima con el mismo ID que la materia prima de este ProductRawMaterial.
            RawMaterial dbRawMaterial = repositoryRawMaterial.findById(rawMaterialId)
                // Si no se encuentra la materia prima, lanza una excepción.
                .orElseThrow(() -> new NotFoundRawMaterialExceptions(Message.NOT_FOUND_RAW_MATERIAL, HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND));

            // Establece la materia prima de este ProductRawMaterial como la materia prima encontrada en la base de datos.
            productRawMaterial.setRawMaterial(dbRawMaterial);
        }

        // Registra un mensaje de información indicando que el producto se está guardando.
        log.info("SAVE Product");

        // Guarda el producto en la base de datos y devuelve el producto guardado.
        return repository.save(productBody);
    }

    @Transactional
    //@Override
    public Product updateProduct(@NonNull Long id, @NonNull Product productBody) {
        Product product = repository.findById(id).orElseThrow(( ) -> new NotFoundProductExceptions(Message.NOT_FOUND_PRODUCT, HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND));
        BeanUtils.copyProperties(productBody, product, "id", "createAt");
        Product updateProduct = product;
        return repository.save(updateProduct);
    }

    @Transactional
    //@Override
    public void deleteProduct(@NonNull Long id) {
        Objects.requireNonNull(id, "Product body must not be null");
        Product product = repository.findById(id).orElseThrow(() -> new NotFoundProductExceptions(Message.NOT_FOUND_PRODUCT, HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND));
        repository.delete(product);

    }

}
