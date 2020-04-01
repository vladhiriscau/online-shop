package org.fasttrackit.onlineshop.persistance;

import org.fasttrackit.onlineshop.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Page<Product> findByNameContaining(String partialName, Pageable pageable);

    Page<Product> findByNameContainingAndQuantityGreaterThan(
            String partialName, int minQuantity, Pageable pageable);

    //   JPQL syntax
//    @Query("SELECT product FROM Product product WHERE `name` LIKE '%:partialName%'")
   //    or using netive queries
    @Query(value = "select * from product where `name` like '%?0'", nativeQuery = true)
    Page<Product> findByPartialName(String partialName, Pageable pageable);
}
