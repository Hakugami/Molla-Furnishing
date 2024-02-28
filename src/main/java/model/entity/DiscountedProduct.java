package model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.Data;

import java.sql.Date;

@Data
@Entity
@Table(name = "discounted_products")
public class DiscountedProduct extends Product {
    @OneToOne
    @JoinColumn(name = "product_id")
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