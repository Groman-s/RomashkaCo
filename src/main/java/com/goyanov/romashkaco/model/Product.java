package com.goyanov.romashkaco.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product implements Cloneable
{
    private Long id;

    @Size(max = 255, message = "Название товара не должно быть длиннее 255 символов!")
    @NotBlank(message = "Товар обязательно должен иметь название!")
    private String name;

    @Size(max = 4096, message = "Описание слишком большое!")
    private String description;

    @Min(value = 0, message = "Стоимость товара не может быть отрицательной!")
    private BigDecimal price = BigDecimal.ZERO;

    private Boolean inStock = false;

    @Override
    public Product clone()
    {
        try { return (Product) super.clone(); }
        catch (CloneNotSupportedException e) { throw new RuntimeException(e); }
    }
}
