package com.assaf.StoreApi.products;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/add")
    public ResponseEntity<Product> addProduct(
            @ModelAttribute ProductRequest productRequest
    ) throws IOException {
        return productService.addProduct(productRequest);
    }

    @PostMapping("/edit")
    public ResponseEntity<Product> editProduct(
            @ModelAttribute ProductRequest productRequest
    ) throws IOException {
        return productService.editProduct(productRequest);
    }

    @DeleteMapping("/delete/{product_id}")
    protected ResponseEntity<Boolean> deleteProduct(
            @PathVariable(name = "product_id") Long productId
    ){
        return productService.deleteProduct(productId);
    }

    @GetMapping("/get_all")
    public ResponseEntity<List<Product>> getAllProducts() {
        return productService.getAllProducts();
    }
}
