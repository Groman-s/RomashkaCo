package com.goyanov.romashkaco.exceptions.not.found;

public class EntityNotFoundException extends RuntimeException
{
    public EntityNotFoundException()
    {
        super("Сущность с таким id не найдена!");
    }

    public EntityNotFoundException(String message)
    {
        super(message);
    }
}
