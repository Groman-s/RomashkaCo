package com.goyanov.romashkaco.repositories;

import com.goyanov.romashkaco.model.ProductSale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductsSalesRepository extends JpaRepository<ProductSale, Long>
{

}
