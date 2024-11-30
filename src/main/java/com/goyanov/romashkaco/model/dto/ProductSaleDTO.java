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
    private Long productArticle;
    private Integer amount;
    private BigDecimal salePrice;

    public ProductSaleDTO(ProductSale productSale)
    {
        this.documentName = productSale.getDocumentName();
        this.productArticle = productSale.getProduct().getArticle();
        this.amount = productSale.getAmount();
        this.salePrice = productSale.getSalePrice();
    }
}
