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

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product_delivery")
public class ProductDelivery
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_delivery_id")
    private Long id;

    @Column(name = "document_name")
    @NotBlank(message = "Заполните название документа!")
    @Check(constraints = "trim(name) <> ''")
    @Size(max = 255, message = "Название документа не может быть длиннее 255 символов!")
    private String documentName;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "amount")
    @Min(value = 1, message = "Минимально количество товаров для поставки: 1")
    @ColumnDefault("1")
    private Integer amount = 1;
}
