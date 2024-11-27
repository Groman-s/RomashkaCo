package com.goyanov.romashkaco.model.dto;

import com.goyanov.romashkaco.model.Product;
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
public class ProductDTO
{
    @NotBlank(message = "Товар обязательно должен иметь название!")
    @Size(max = 255, message = "Название товара не должно быть длиннее 255 символов!")
    private String name;

    @Size(max = 4096, message = "Описание слишком большое!")
    private String description;

    @Min(value = 0, message = "Стоимость товара не может быть отрицательной!")
    private BigDecimal price = BigDecimal.ZERO;

    private Boolean inStock = false;

    public ProductDTO(Product product)
    {
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.inStock = product.getInStock();
    }
}
