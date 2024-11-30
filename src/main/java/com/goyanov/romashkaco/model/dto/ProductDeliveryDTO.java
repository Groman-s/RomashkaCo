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
    private Long productId;
    private Integer amount;

    public ProductDeliveryDTO(ProductDelivery productDelivery)
    {
        this.documentName = productDelivery.getDocumentName();
        this.productId = productDelivery.getProduct().getId();
        this.amount = productDelivery.getAmount();
    }
}
