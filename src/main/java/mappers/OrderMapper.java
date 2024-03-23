package mappers;

import models.DTOs.*;
import models.entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderMapper {

    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    Order orderDtoToOrder(OrderDto orderDto);
    OrderDto orderToOrderDto(Order order);

    DiscountedProduct discountedProductDtoToDiscountedProduct(DiscountedProductDto discountedProductDto);
    DiscountedProductDto discountedProducToDiscountedProductDto(DiscountedProduct discountedProduct);

}
