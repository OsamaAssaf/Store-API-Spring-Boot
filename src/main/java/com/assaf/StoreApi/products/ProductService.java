package com.assaf.StoreApi.products;

import com.assaf.StoreApi.enums.Role;
import com.assaf.StoreApi.hendlers.custom.AllFieldRequiredException;
import com.assaf.StoreApi.hendlers.custom.IdRequiredException;
import com.assaf.StoreApi.hendlers.custom.ImageNotAddedException;
import com.assaf.StoreApi.user.User;
import com.assaf.StoreApi.utils.Constants;
import com.assaf.StoreApi.utils.FileUploadUtil;
import com.assaf.StoreApi.utils.Methods.UserMethods;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final UserMethods userMethods;

    private void checkAuthorities() throws AccessDeniedException {
        String userAuth = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
        if (!userAuth.contains(Role.STORE_ADMIN.toString()) && !userAuth.contains(Role.SUPER_ADMIN.toString())) {
            throw new AccessDeniedException("Access Denied|الوصول مرفوض");
        }
    }
    private Boolean inputValidation(ProductRequest productRequest , boolean isEdit) {
        if(isEdit){
            if(productRequest.getId() == null){
                throw new IdRequiredException("Id Required|المعرف مطلوب");
            }
        }
        return productRequest != null
                && productRequest.getNameEn() != null
                && productRequest.getNameAr() != null
                && productRequest.getDescriptionEn() != null
                && productRequest.getDescriptionAr() != null
                && productRequest.getPrice() != null
                && productRequest.getImage() != null;
    }
    private String uploadImage(MultipartFile image, Long productId) throws IOException {
        deleteImage(productId);
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(image.getOriginalFilename()));
        String uploadDir = Constants.SERVICE_PROVIDER_PHOTOS_PATH + "/" + productId;
        FileUploadUtil.saveFile(uploadDir, fileName, image);
        String imagePath = Constants.URL_PREFIX + Constants.DOMAIN_NAME + "/" + uploadDir + "/" + image.getOriginalFilename();
        int isSuccess = productRepository.updateImageById(productId,imagePath);
        if(isSuccess != 1){
            throw new ImageNotAddedException("Image Not Added|خطأ في إضافة الصورة");
        }
        return imagePath;
    }
    private boolean deleteImage(long productId) throws IOException {
        String filePath = Constants.SERVICE_PROVIDER_PHOTOS_PATH + "/" + productId;
        return FileUploadUtil.deleteFile(filePath);
    }
    protected ResponseEntity<Product> addProduct(ProductRequest productRequest) throws IOException {
        if (!inputValidation(productRequest,false)) {
            throw new AllFieldRequiredException("All Field Required|جميع الحقول مطلوبة");
        }

        Long currentUserId = userMethods.getCurrentUserId();
        if(currentUserId == null){
            throw new AccessDeniedException("Access Denied|الوصول مرفوض");
        }

        checkAuthorities();
        Product product = Product
                .builder()
                .nameEn(productRequest.getNameEn())
                .nameAr(productRequest.getNameAr())
                .descriptionEn(productRequest.getDescriptionEn())
                .descriptionAr(productRequest.getDescriptionAr())
                .price(productRequest.getPrice())
//                .store(new User(currentUserId))
                .build();
        Product savedproduct = productRepository.save(product);

        String imagePath = uploadImage(productRequest.getImage(),savedproduct.getId());
        savedproduct.setImageUrl(imagePath);
        return ResponseEntity.ok(savedproduct);
    }
    protected ResponseEntity<Product> editProduct(ProductRequest productRequest) throws IOException {
        if (!inputValidation(productRequest,true)) {
            throw new AllFieldRequiredException("All Field Required|جميع الحقول مطلوبة");
        }

        checkAuthorities();
        Product product = Product
                .builder()
                .id(productRequest.getId())
                .nameEn(productRequest.getNameEn())
                .nameAr(productRequest.getNameAr())
                .descriptionEn(productRequest.getDescriptionEn())
                .descriptionAr(productRequest.getDescriptionAr())
                .price(productRequest.getPrice())
                .build();

        Product savedproduct = productRepository.save(product);

        if(productRequest.getImage() != null){
            String imagePath = uploadImage(productRequest.getImage(),savedproduct.getId());
            savedproduct.setImageUrl(imagePath);
        }
        return ResponseEntity.ok(savedproduct);
    }
    protected ResponseEntity<Boolean> deleteProduct(Long productId){
        try {
            productRepository.deleteById(productId);
            boolean isSuccess = deleteImage(productId);
            return ResponseEntity.ok(isSuccess);
        } catch (IllegalArgumentException ignored) {
            return ResponseEntity.ok(false);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productRepository.findAll());
    }
}
