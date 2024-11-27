package com.goyanov.romashkaco.model;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest
{
    private static Validator validator;

    @BeforeAll
    public static void initValidator()
    {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    public void checkValidatesName1()
    {
        Product product = new Product(1L, " ", "", BigDecimal.valueOf(300), true);
        Set<ConstraintViolation<Product>> validate = validator.validate(product);
        assertEquals(1, validate.size());
    }

    @Test
    public void checkValidatesName2()
    {
        Product product = new Product(1L, "Лопата", "", BigDecimal.valueOf(300), true);
        Set<ConstraintViolation<Product>> validate = validator.validate(product);
        assertTrue(validate.isEmpty());
    }

    @Test
    public void checkValidatesPrice1()
    {
        Product product = new Product(1L, "Дверь", "", BigDecimal.valueOf(-100), true);
        Set<ConstraintViolation<Product>> validate = validator.validate(product);
        assertEquals(1, validate.size());
    }

    @Test
    public void checkValidatesPrice2()
    {
        Product product = new Product(1L, "Дверь", "", BigDecimal.valueOf(500), true);
        Set<ConstraintViolation<Product>> validate = validator.validate(product);
        assertTrue(validate.isEmpty());
    }

    @Test
    public void testMultipleValidationErrors()
    {
        Product product = new Product(1L, " ", "", BigDecimal.valueOf(-500), true);
        Set<ConstraintViolation<Product>> validate = validator.validate(product);
        assertEquals(2, validate.size());
    }
}