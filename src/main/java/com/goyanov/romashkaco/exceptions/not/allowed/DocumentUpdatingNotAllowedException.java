package com.goyanov.romashkaco.exceptions.not.allowed;

public class DocumentUpdatingNotAllowedException extends RuntimeException
{
    public DocumentUpdatingNotAllowedException()
    {
        super("Изменять этот вид документов нельзя.");
    }

    public DocumentUpdatingNotAllowedException(String message)
    {
        super(message);
    }
}
