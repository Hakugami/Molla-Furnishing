package models.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.Getter;

import java.util.Date;

@Getter
@Entity
@Table(name = "discounted_products")
public class DiscountedProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id", nullable = false, referencedColumnName = "productId")
    private Product product;

    @Min(0)
    @Column(nullable = false)
    private double discountRate;

    @Min(0)
    @Column(nullable = false)
    private double discountedPrice;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date startDate;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date endDate;

    @PostLoad
    @PostPersist
    public void calculateDiscountedPrice() {
        if (product != null) {
            this.discountedPrice = product.getPrice() * (1 - this.discountRate);
        }
    }

}