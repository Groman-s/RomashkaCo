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
@MappedSuperclass
public abstract class ProductTransaction
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_delivery_id")
    protected Long id;

    @Column(name = "document_name", nullable = false)
    @NotBlank(message = "Заполните название документа!")
    @Check(constraints = "trim(document_name) <> ''")
    @Size(max = 255, message = "Название документа не может быть длиннее 255 символов!")
    protected String documentName;

    @ManyToOne
    @JoinColumn(name = "product_article", referencedColumnName = "article", nullable = false)
    protected Product product;

    @Column(name = "amount", nullable = false)
    @Min(value = 1, message = "Минимально количество товаров для поставки: 1")
    @ColumnDefault("1")
    protected Integer amount = 1;
}
