package com.example.que_fresuki.services.servicesImpl;

import com.example.que_fresuki.entitys.models.Ingredient;
import com.example.que_fresuki.exceptions.ingredientExceptions.InvalidIngredientExceptions;
import com.example.que_fresuki.exceptions.ingredientExceptions.NotFoundIngredientExceptions;
import com.example.que_fresuki.repositorys.IIngredientRepository;
import com.example.que_fresuki.services.IngredientService;

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
public class IngredientServiceImpl implements IngredientService {

    private final IIngredientRepository ingredientRepository;

    @Autowired
    public IngredientServiceImpl(IIngredientRepository iIngredientRepository){
        this.ingredientRepository = iIngredientRepository;
    }

    @Override
    public List<Ingredient> getIngredients() {
        log.info(" LISTADO DE INGREDIENTES ");
        return ingredientRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Ingredient getIngredient(@NonNull Long id) {
        return ingredientRepository.findById(id).orElseThrow( () -> new NotFoundIngredientExceptions(Message.NOT_FOUND_PRODUCT, HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND));
    }

    @Override
    public Ingredient saveIngredient(Ingredient ingredient) {
        Optional<Ingredient> ingredientFound = ingredientRepository.findByName(ingredient.getName());
        if(ingredientFound.isPresent()){
            throw new InvalidIngredientExceptions(Message.INGREDIENT_ALREADY_EXISTS, HttpStatus.CONFLICT.value(), HttpStatus.CONFLICT);
        }
        log.info("SAVE Ingredient");
        return ingredientRepository.save(ingredient);
    }

    @Override
    public Ingredient updateIngredient(@NonNull Long id, @NonNull Ingredient ingredient) {
        Optional<Ingredient> ingredientFound = ingredientRepository.findByName(ingredient.getName());
        if (ingredientFound.isEmpty()){
            throw new NotFoundIngredientExceptions(Message.NOT_FOUND_INGREDIENT, HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND);
        }
        BeanUtils.copyProperties(ingredient, ingredientFound, "id", "createAt");
        Ingredient updatedIngredient = ingredientFound.get();
        return ingredientRepository.save(updatedIngredient);
    }

    @Override
    public void deleteIngredient(@NonNull Long id) {
        Objects.requireNonNull(id, "Ingredient body must not be null");
        Ingredient ingredient = ingredientRepository.findById(id).orElseThrow( () -> new NotFoundIngredientExceptions(Message.NOT_FOUND_INGREDIENT, HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND));
        ingredientRepository.delete(ingredient);
    }
}
