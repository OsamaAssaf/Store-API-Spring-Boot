package com.assaf.StoreApi.products;

public abstract class AbstractProductRequest {

    public void setName_en(String nameEn) {
        setNameEn(nameEn);
    }
    public void setName_ar(String nameAr) {
        setNameAr(nameAr);
    }
    public void setDescription_en(String descriptionEn) {
        setDescriptionEn(descriptionEn);
    }
    public void setDescription_ar(String descriptionAr) {
        setDescriptionAr(descriptionAr);
    }
    abstract void setNameEn(String nameEn);
    abstract void setNameAr(String nameAr);
    abstract void setDescriptionEn(String descriptionEn);
    abstract void setDescriptionAr(String descriptionAr);


}
