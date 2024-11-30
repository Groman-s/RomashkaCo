package com.goyanov.romashkaco.model.dto;

import com.goyanov.romashkaco.model.ProductDelivery;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDeliveryDTO
{
    private String documentName;
    private Long productArticle;
    private Integer amount;

    public ProductDeliveryDTO(ProductDelivery productDelivery)
    {
        this.documentName = productDelivery.getDocumentName();
        this.productArticle = productDelivery.getProduct().getArticle();
        this.amount = productDelivery.getAmount();
    }
}
