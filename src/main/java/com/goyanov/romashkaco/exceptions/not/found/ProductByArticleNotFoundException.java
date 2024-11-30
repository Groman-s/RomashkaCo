package com.goyanov.romashkaco.exceptions.not.found;

public class ProductByArticleNotFoundException extends EntityNotFoundException
{
    public ProductByArticleNotFoundException()
    {
        super("Продукт с таким артикулом не найден!");
    }
}
