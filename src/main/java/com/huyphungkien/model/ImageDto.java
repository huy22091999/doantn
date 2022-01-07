package com.huyphungkien.model;

import com.huyphungkien.domain.Image;

public class ImageDto {
    private Long id;
    private String img;
    private ProductDto product;

    public ImageDto() {
    }
    public ImageDto(Image image) {
        this.id=image.getId();
        this.img=image.getImg();
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public ProductDto getProduct() {
        return product;
    }

    public void setProduct(ProductDto product) {
        this.product = product;
    }
}
