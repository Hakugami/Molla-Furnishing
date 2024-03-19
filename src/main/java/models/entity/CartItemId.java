package models.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Setter
@Getter

@NoArgsConstructor
@Embeddable
public class CartItemId implements Serializable {
    @Column(name = "product_id")
    private Long productId;

    @Column(name = "shopping_cart_id")
    private Long shoppingCartId;

    public CartItemId(Long productId, Long shoppingCartId) {
        this.productId = productId;
        this.shoppingCartId = shoppingCartId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if(o==null || getClass() != o.getClass()) return false;

        CartItemId that = (CartItemId) o;
        return Objects.equals(productId, that.productId) && Objects.equals(shoppingCartId, that.shoppingCartId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, shoppingCartId);
    }
}