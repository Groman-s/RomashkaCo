package com.goyanov.romashkaco.repositories;

import com.goyanov.romashkaco.model.Product;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ProductsRepository
{
    private final Map<Long, Product> products = new HashMap<>();
    private final AtomicLong lastGeneratedId =  new AtomicLong(0);

    public List<Product> findAll()
    {
        return new ArrayList<>(products.values());
    }

    public Optional<Product> findById(Long id)
    {
        return Optional.ofNullable(products.get(id));
    }

    public void save(Product product)
    {
        if (product.getId() == null)
        {
            product.setId(lastGeneratedId.incrementAndGet());
        }
        products.put(product.getId(), product);
    }

    public void deleteById(Long id)
    {
        products.remove(id);
    }
}
