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
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product")
public class Product
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @Column(name = "name", nullable = false)
    @Check(constraints = "trim(name) <> ''")
    @Size(max = 255, message = "Название товара не должно быть длиннее 255 символов!")
    @NotBlank(message = "Товар обязательно должен иметь название!")
    private String name;

    @Column(name = "description")
    @Size(max = 4096, message = "Описание слишком большое!")
    private String description;

    @Column(name = "price", nullable = false)
    @ColumnDefault("0")
    @Min(value = 0, message = "Стоимость товара не может быть отрицательной!")
    private BigDecimal price = BigDecimal.ZERO;

    @Column(name = "in_stock", nullable = false)
    @ColumnDefault("false")
    private Boolean inStock = false;

    @Column(name = "amount", nullable = false)
    private Integer amount = 0;

    @OneToMany(mappedBy = "product")
    private List<ProductDelivery> deliveries = new ArrayList<>();

    @OneToMany(mappedBy = "product")
    private List<ProductSale> sales = new ArrayList<>();
}
