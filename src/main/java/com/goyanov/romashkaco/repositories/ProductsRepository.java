package com.goyanov.romashkaco.repositories;

import com.goyanov.romashkaco.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductsRepository extends JpaRepository<Product, Long>
{

}
