package mappers;

import models.DTOs.DiscountedProductDto;
import models.DTOs.MyProfileOrderDto;
import models.DTOs.OrderDto;
import models.entity.DiscountedProduct;
import models.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderMapper {

    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    Order orderDtoToOrder(OrderDto orderDto);

    OrderDto orderToOrderDto(Order order);

    MyProfileOrderDto orderToMyProfileOrderDto(Order order);

    DiscountedProduct discountedProductDtoToDiscountedProduct(DiscountedProductDto discountedProductDto);

    DiscountedProductDto discountedProducToDiscountedProductDto(DiscountedProduct discountedProduct);

}
