package models.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "shopping_carts")
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Setter
    @OneToOne(mappedBy = "cart")
    private User user;

    @Setter
    @OneToMany(mappedBy = "shoppingCart", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<CartItem> cartItems;

    @Setter
    @Column(name = "total_amount")
    private Double totalAmount;

    @Setter
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdate;


    public void addCartItem(Product product, int quantity ,Double totalAmount) {
        {
            if (cartItems == null) {
                cartItems = new ArrayList<>();
            }
            CartItem cartItem = new CartItem(product, quantity, this);
            cartItems.add(cartItem);
            this.totalAmount = totalAmount;
        }

    }

    @PostPersist
    @PostUpdate
    @PostRemove
    public void updateLastUpdate() {
        lastUpdate = new Date();
    }


    public void removeProduct(Product product) {
        for(Iterator<CartItem> iterator = cartItems.iterator(); iterator.hasNext();) {
            CartItem cartItem = iterator.next();
            if(cartItem.getProduct().equals(product) && cartItem.getShoppingCart().equals(this)) {
                iterator.remove();
                cartItem.setShoppingCart(null);
                cartItem.setProduct(null);
            }
        }
    }


}