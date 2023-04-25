package com.assaf.StoreApi.products;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface ProductRepository extends JpaRepository<Product,Long> {

    /// UPDATE products SET image_url = "123" WHERE id = 10;
    @Modifying
    @Query(
            nativeQuery = true,
            value = "UPDATE products SET image_url = :imageUrl WHERE id = :productId"
    )
    int updateImageById(Long productId, String imageUrl);

//    @Modifying
    @Query(
            nativeQuery = true,
            value = "UPDATE products SET :query WHERE id = :id"
    )
    Product editCustomQuery(String query,Long id);
}
