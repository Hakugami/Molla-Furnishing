package models.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.Instant;
import java.time.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Setter
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<OrderItem> orderItems;

    @Setter
    @Column(nullable = false)
    private double totalAmount;

    @Setter
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id")
    private Address shippingAddress;

    @Setter
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDate;

    @Transient
    private LocalDate date;

    @Transient
    private LocalTime time;

    public void addOrderItems(Product product, int quantity, double totalAmount) {
        if (orderItems == null) {
            orderItems = new ArrayList<>();
        }
        OrderItem orderItem = new OrderItem(product, quantity, this);
        orderItems.add(orderItem);
        this.totalAmount = totalAmount;
    }

    @PrePersist
    @PreUpdate
    @PreRemove
    public void updateOrderDate() {
        orderDate = new Date();
    }

    @PostLoad
    public void setTransientFields() {
        this.date = orderDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        this.time = orderDate.toInstant().atZone(ZoneId.systemDefault()).toLocalTime();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order order)) return false;

        return Objects.equals(id, order.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}