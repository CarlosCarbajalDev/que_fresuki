package com.example.que_fresuki.services.servicesImpl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.que_fresuki.entitys.models.OptionLevel;
import com.example.que_fresuki.entitys.models.ProductOption;
import com.example.que_fresuki.exceptions.productOptionExceptions.NotFoundProductOptionExceptions;
import com.example.que_fresuki.repositorys.IOptionLevelRepository;
import com.example.que_fresuki.repositorys.IProductOptionRepository;
import com.example.que_fresuki.services.ProductOptionService;
import com.example.que_fresuki.utils.Message;

import io.micrometer.common.lang.NonNull;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class ProductOptionServiceImpl implements ProductOptionService{

    private final IProductOptionRepository productOptionRepository;
    private final IOptionLevelRepository optionLevelRepository; 

    @Autowired
    public ProductOptionServiceImpl(IProductOptionRepository productOptionRepository, IOptionLevelRepository optionLevelRepository) {
        this.productOptionRepository = productOptionRepository;
        this.optionLevelRepository = optionLevelRepository;
    }

    @Override
    public List<ProductOption> getAllProductOption() {
        log.info(" LISTADO DE OPCIONES DE PRODUCTOS ");
        return productOptionRepository.findAll();
    }

    @Override
    public ProductOption getProductOption(@NonNull Long id) {
        return productOptionRepository.findById(id).orElseThrow( () -> new NotFoundProductOptionExceptions(Message.NOT_FOUND_PRODUCT_OPTION, HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND) );
    }

    /**
     * Guarda un ProductOption en la base de datos.
     * 
     * @param productOption El ProductOption a guardar.
     * @return El ProductOption guardado.
     * @throws NotFoundProductOptionExceptions Si ya existe un ProductOption con el mismo nombre.
     */
    @Override
    public ProductOption saveProductOption(ProductOption productOption) {
        // Obtiene el OptionLevel del ProductOption
        OptionLevel optionLevel = productOption.getOptionLevel();

        // Si el OptionLevel no es nulo
        if(optionLevel != null) {
            // Busca un OptionLevel existente con el mismo nombre
            Optional<OptionLevel> existingOptionLevel = optionLevelRepository.findByNameIgnoreCase(optionLevel.getName());

            // Si el OptionLevel existe, lo establece en el ProductOption
            if (existingOptionLevel.isPresent()) {
                productOption.setOptionLevel(existingOptionLevel.get());
            } else {
                // Si el OptionLevel no existe, lo guarda en la base de datos y lo establece en el ProductOption
                optionLevel = optionLevelRepository.save(optionLevel);
                productOption.setOptionLevel(optionLevel);
            }
        }

        // Busca un ProductOption existente con el mismo nombre
        Optional<ProductOption> existingProductOption = productOptionRepository.findByNameIgnoreCase(productOption.getName());

        // Si el ProductOption existe, lanza una excepción
        if(existingProductOption.isPresent() ) {
            throw new NotFoundProductOptionExceptions(Message.PRODUCT_OPTION_ALREADY_EXISTS, HttpStatus.CONFLICT.value(), HttpStatus.CONFLICT);
        }

        // Registra en el log que se va a guardar el ProductOption
        log.info("SAVE ProductOption");

        // Guarda el ProductOption en la base de datos y lo devuelve
        return productOptionRepository.save(productOption);
    }

    @Override
    public ProductOption updateProductOption(@NonNull Long id, ProductOption body) {
        Optional<ProductOption> productOption = productOptionRepository.findByNameIgnoreCase(body.getName());
        if(productOption.isEmpty()) {
            throw new NotFoundProductOptionExceptions(Message.NOT_FOUND_PRODUCT_OPTION, HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND);
        }
        BeanUtils.copyProperties(body, productOption);
        ProductOption updatedProductOption = productOption.get();
        log.info("UPDATE ProductOption");
        return productOptionRepository.save(updatedProductOption);
    }

    @Override
    public void deleteProductOption(Long id) {
        Objects.requireNonNull(id, "ProductOption id must not be null");
        ProductOption productOption = productOptionRepository.findById(id)
            .orElseThrow(() -> new NotFoundProductOptionExceptions(Message.NOT_FOUND_PRODUCT_OPTION, HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND));
        productOptionRepository.delete(productOption);
    }
    
}
