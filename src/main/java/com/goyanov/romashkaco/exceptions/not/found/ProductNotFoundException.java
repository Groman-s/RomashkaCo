package com.goyanov.romashkaco.exceptions.not.found;

public class ProductNotFoundException extends EntityNotFoundException
{
    public ProductNotFoundException()
    {
        super("Продукт с таким id не найден!");
    }
}
