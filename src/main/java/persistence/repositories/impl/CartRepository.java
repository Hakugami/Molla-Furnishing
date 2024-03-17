package persistence.repositories.impl;

import models.entity.CartItem;
import models.entity.Category;
import persistence.repositories.GenericRepository;

public class CartRepository extends GenericRepository<CartItem ,Long>{
    public CartRepository()
    {
        super(CartItem.class);
    }


}
