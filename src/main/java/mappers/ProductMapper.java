package mappers;

import models.DTOs.DiscountedProductDto;
import models.DTOs.ProductDto;
import models.entity.DiscountedProduct;
import models.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {

    //instance of the mapper
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mapping(source = "discountedProduct", target = "discountedProduct", qualifiedByName = "discountedProductDtoToDiscountedProduct")
    Product productDtoToProduct(ProductDto productDto);

    @Mapping(source = "discountedProduct", target = "discountedProduct", qualifiedByName = "discountedProductToDiscountedProductDto")
    ProductDto productToProductDto(Product product);

    @Named("discountedProductDtoToDiscountedProduct")
    DiscountedProduct discountedProductDtoToDiscountedProduct(DiscountedProductDto discountedProductDto);

    @Named("discountedProductToDiscountedProductDto")
    DiscountedProductDto discountedProductToDiscountedProductDto(DiscountedProduct discountedProduct);


}