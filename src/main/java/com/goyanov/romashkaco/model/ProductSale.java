package com.goyanov.romashkaco.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Check;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product_sale")
public class ProductSale extends ProductTransaction
{
    @Column(name = "sale_price", nullable = false)
    @Min(value = 0, message = "Стоимость продажи не может быть отрицательной!")
    @ColumnDefault("0")
    private BigDecimal salePrice = BigDecimal.ZERO;
}
