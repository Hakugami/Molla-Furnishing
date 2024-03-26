package mappers;

import models.DTOs.CartItemDto;
import models.entity.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CartMapper {
    CartMapper INSTANCE = Mappers.getMapper(CartMapper.class);

    CartItemDto cartItemToCartItemDto(CartItem cartItem);


}
