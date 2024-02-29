package com.example.que_fresuki.utils;

public record Message() {
    public static final String NOT_FOUND_PRODUCT = "product not found";
    public static final String NOT_FOUND_INGREDIENT = "ingredient not found";
    public static final String NOT_FOUND_RAW_MATERIAL = "raw material not found";
    public static final String NOT_FOUND_PRODUCT_OPTION = "product option not found";
    public static final String NOT_FOUND_OPTION_LEVEL = "option level not found";

    public static final String PRODUCT_ALREADY_EXISTS = "there is already a product with this ID" ;
    public static final String INGREDIENT_ALREADY_EXISTS = "there is already a ingredient with this ID" ;
    public static final String RAW_MATERIAL_ALREADY_EXISTS = "there is already a raw material with this ID" ;
    public static final String PRODUCT_OPTION_ALREADY_EXISTS = "there is already a product option with this ID" ;
    public static final String PRODUCT_LEVEL_ALREADY_EXISTS = "there is already a product level with this ID" ;


    public static final String PRODUCT_REMOVED_SUCCESSFULLY = "product removed successfully";
    public static final String INGREDIENT_REMOVED_SUCCESSFULLY = "ingredient removed successfully";
    public static final String RAW_MATERIAL_REMOVED_SUCCESSFULLY = "raw material removed successfully";
    public static final String PRODUCT_OPTION_REMOVED_SUCCESSFULLY = "product option removed successfully";
    public static final String PRODUCT_LEVEL_REMOVED_SUCCESSFULLY = "product level removed successfully";
    

    public static final String USERNAME_ALREADY_EXISTS = "there is already a user with this";

    public static final String JWT_EXPIRED = "static token expired";

}
