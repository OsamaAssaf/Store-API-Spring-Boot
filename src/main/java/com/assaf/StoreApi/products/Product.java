package com.assaf.StoreApi.products;


import com.assaf.StoreApi.store.Store;
import com.assaf.StoreApi.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name_en")
    private String nameEn;
    @Column(name = "name_ar")
    private String nameAr;
    @Column(name = "description_en")
    private String descriptionEn;
    @Column(name = "description_ar")
    private String descriptionAr;
    private double price;
    private String imageUrl;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;


}
