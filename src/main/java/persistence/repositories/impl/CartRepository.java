package persistence.repositories.impl;

import models.entity.CartItem;
import models.entity.Category;
import models.entity.ShoppingCart;
import persistence.repositories.GenericRepository;

public class CartRepository extends GenericRepository<ShoppingCart,Long>{
    public CartRepository()
    {
        super(ShoppingCart.class);
    }
}
