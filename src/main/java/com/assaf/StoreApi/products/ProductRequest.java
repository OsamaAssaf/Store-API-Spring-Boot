package com.assaf.StoreApi.products;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@EqualsAndHashCode(callSuper = true)
@Data
//@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest extends AbstractProductRequest{

    private Long id;
    @JsonProperty("name_en")
    private String nameEn;
    @JsonProperty("name_ar")
    private String nameAr;
    @JsonProperty("description_en")
    private String descriptionEn;
    @JsonProperty("description_ar")
    private String descriptionAr;
    private Double price;
    @JsonProperty("image")
    private MultipartFile image;
}
