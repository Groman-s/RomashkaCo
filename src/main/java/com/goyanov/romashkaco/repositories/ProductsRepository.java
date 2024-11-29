package com.goyanov.romashkaco.repositories;

import com.goyanov.romashkaco.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductsRepository extends JpaRepository<Product, Long>
{
    @Query
    (
        "from Product p " +
        "where lower(p.name) like '%'||lower(:keyWord)||'%' " +
        "or lower(p.description) like '%'||lower(:keyWord)||'%' " +
        "order by case when lower(p.name) like '%'||lower(:keyWord)||'%' then 1 else 2 end"
    )
    Page<Product> findByKeyWord(String keyWord, Pageable pageable);
}
