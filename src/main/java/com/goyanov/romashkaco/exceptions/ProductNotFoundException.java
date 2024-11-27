package com.goyanov.romashkaco.exceptions;

public class ProductNotFoundException extends RuntimeException
{
    public ProductNotFoundException()
    {
        super("Продукт с таким id не найден!");
    }

    public ProductNotFoundException(String message)
    {
        super(message);
    }
}
