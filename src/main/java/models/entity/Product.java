package models.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
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
    private long productId;

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
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateAdded;

    @Setter
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Rating> ratings;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "product_images", joinColumns = @JoinColumn(name = "product_id"))
    @Column(name = "image_url")
    private List<String> images;

    @Setter
    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private DiscountedProduct discountedProduct;

    @Setter
    @Transient
    private double rating;

    @Setter
    @Transient
    private String categoryName;

    @PostLoad
    public void postLoad() {
        this.rating = getRating();
        this.categoryName = category.getName();
    }

    public double getRating() {
        return ratings.stream().mapToDouble(Rating::getValue).average().orElse(0.0);
    }


}