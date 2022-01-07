package com.huyphungkien.domain;

import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name="tbl_image")
public class Image {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Type(type = "text")
    private String img;

    @ManyToOne
    @JoinColumn(name = "id_product")
    private Product product;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Image() {
    }
}
