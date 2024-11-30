package com.goyanov.romashkaco.model;

import jakarta.persistence.*;

@Entity
@Table(name = "product_delivery")
public class ProductDelivery extends ProductTransaction
{
    @PreUpdate
    @PrePersist
    public void onSave()
    {
        product.setAmount(product.getAmount() + amount);
    }
}
