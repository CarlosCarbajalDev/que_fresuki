package com.example.que_fresuki.services.servicesImpl;

import com.example.que_fresuki.entitys.models.Product;
import com.example.que_fresuki.entitys.models.RawMaterial;
import com.example.que_fresuki.exceptions.InvalidProductExceptions;
import com.example.que_fresuki.exceptions.NotFoundProductExceptions;
import com.example.que_fresuki.exceptions.rawMaterialExceptions.NotFoundRawMaterialExceptions;
import com.example.que_fresuki.repositorys.IProductRepository;
import com.example.que_fresuki.repositorys.IRawMaterialRepository;
import com.example.que_fresuki.services.ProductService;
import com.example.que_fresuki.utils.Message;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Log4j2
public class ProductServiceImpl implements ProductService {

    private final IProductRepository repository;
    private final IRawMaterialRepository repositoryRawMaterial;

    @Autowired
    public ProductServiceImpl(IProductRepository repository, IRawMaterialRepository repositoryRawMaterial){
        this.repository = repository;
        this.repositoryRawMaterial = repositoryRawMaterial;
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
        return repository.findById(id).orElseThrow(() -> new NotFoundProductExceptions(Message.NOT_FOUND_PRODUCT, HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND));
    }

    @Transactional
    //@Override
    public Product saveProduct(Product body) {
        Optional<Product> productId = repository.findByName(body.getName());
        if(productId.isPresent()){
            throw new InvalidProductExceptions(Message.PRODUCT_ALREADY_EXISTS,HttpStatus.CONFLICT.value(),HttpStatus.CONFLICT);
        }

        for (RawMaterial rawMaterial: body.getRawMaterials()) {
           RawMaterial currentRawMaterial = repositoryRawMaterial.findById(rawMaterial.getId()).orElseThrow(() -> new NotFoundRawMaterialExceptions(Message.NOT_FOUND_RAW_MATERIAL, HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND));;
           currentRawMaterial.setTotalQuantity(currentRawMaterial.getTotalQuantity());
        }
        log.info("SAVE Product");

        return repository.save(body);
    }
    @Transactional
    //@Override
    public Product updateProduct(@NonNull Long id, @NonNull Product body) {
        Product product = repository.findById(id).orElseThrow(( ) -> new NotFoundProductExceptions(Message.NOT_FOUND_PRODUCT, HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND));
        BeanUtils.copyProperties(body, product, "id", "createAt");
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
