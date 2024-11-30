package com.goyanov.romashkaco.exceptions.not.found;

public class ProductSaleNotFoundException extends EntityNotFoundException
{
    public ProductSaleNotFoundException()
    {
        super("Документ продажи товара с таким id не найден!");
    }
}
