package com.goyanov.romashkaco.exceptions.not.found;

public class ProductDeliveryNotFound extends EntityNotFoundException
{
    public ProductDeliveryNotFound()
    {
        super("Документ поставки товара с таким id не найден!");
    }
}
