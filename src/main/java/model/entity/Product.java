package model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
@Inheritance(strategy = InheritanceType.JOINED)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Setter
    @Column(nullable = false)
    private String name;

    @Setter
    @Column(nullable = false)
    private String description;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @Setter
    @Column(nullable = false)
    private double price;

    @Setter
    @Column(nullable = false)
    private int quantity;

    @Setter
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Rating> ratings;

    @ElementCollection
    @CollectionTable(name = "product_images", joinColumns = @JoinColumn(name = "product_id"))
    @Column(name = "image_url")
    private List<String> images;

    @Setter
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "discounted_product_id", referencedColumnName = "id")
    private DiscountedProduct discountedProduct;

    public double getRating() {
        return ratings.stream().mapToDouble(Rating::getValue).average().orElse(0.0);
    }

    @PreUpdate
    @PrePersist
    public void updateDiscountedProduct() {
        if (discountedProduct != null) {
            discountedProduct.setName(this.name);
            discountedProduct.setCategory(this.category);
            discountedProduct.setPrice(this.price);
            discountedProduct.setQuantity(this.quantity);
        }
    }

}