package com.goyanov.romashkaco.exceptions;

import com.goyanov.romashkaco.exceptions.not.found.EntityNotFoundException;

public class ProductNotFoundException extends EntityNotFoundException
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
