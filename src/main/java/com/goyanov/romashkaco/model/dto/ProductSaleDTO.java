package com.goyanov.romashkaco.model.dto;

import com.goyanov.romashkaco.model.ProductSale;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductSaleDTO
{
    private String documentName;
    private String productName;
    private Integer amount;
    private BigDecimal price;

    public ProductSaleDTO(ProductSale productSale)
    {
        this.documentName = productSale.getDocumentName();
        this.productName = productSale.getProduct().getName();
        this.amount = productSale.getAmount();
        this.price = productSale.getSalePrice();
    }
}
