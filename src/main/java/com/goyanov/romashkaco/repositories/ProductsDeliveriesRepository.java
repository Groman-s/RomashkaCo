package com.goyanov.romashkaco.repositories;

import com.goyanov.romashkaco.model.ProductDelivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductsDeliveriesRepository extends JpaRepository<ProductDelivery, Long>
{

}
