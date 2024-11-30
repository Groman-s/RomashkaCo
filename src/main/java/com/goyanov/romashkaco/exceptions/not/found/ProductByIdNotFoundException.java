package com.goyanov.romashkaco.exceptions.not.found;

public class ProductByIdNotFoundException extends EntityNotFoundException
{
    public ProductByIdNotFoundException()
    {
        super("Продукт с таким id не найден!");
    }
}
