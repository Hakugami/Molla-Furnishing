package services;

import mappers.ProductMapper;
import mappers.ProductMapperImpl;
import models.DTOs.ProductDto;
import models.entity.Product;
import persistence.repositories.GenericRepository;
import persistence.repositories.helpers.ProductFilter;
import persistence.repositories.impl.ProductRepository;

import java.util.List;
import java.util.Optional;

public class ProductService {
    private static volatile ProductService instance = null;
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    private ProductService() {
        productRepository = new ProductRepository();
        productMapper = ProductMapper.INSTANCE;
    }

    public static ProductService getInstance() {
        if (instance == null) {
            synchronized (ProductService.class) {
                if (instance == null) {
                    instance = new ProductService();
                }
            }
        }
        return instance;
    }

    public List<ProductDto> getProductByFilter(int page, int size, ProductFilter filter) {
        List<Product> products = productRepository.filterProducts(page, size, filter);
        List<ProductDto> productDtos = products.stream().map(productMapper::productToProductDto).toList();
        return productDtos;
    }

    public ProductDto getProductById(long productId) {
        Optional<Product> product = productRepository.read(productId);
        return productMapper.productToProductDto(product.orElseGet(Product::new));
    }


    public long countProducts() {
        return productRepository.countProducts();
    }

    public List<ProductDto> retrieveProducts(int page, int size) {
        List<Product> products = productRepository.retrieveProducts(page, size);
        List<ProductDto> productDtos = products.stream().map(productMapper::productToProductDto).toList();
        return productDtos;
    }

    public List<ProductDto> retrieveProductsByCategory(int page, int size, String category) {
        List<Product> products = productRepository.retrieveProductsByCategory(page, size, category);
        List<ProductDto> productDtos = products.stream().map(productMapper::productToProductDto).toList();
        return productDtos;
    }

    public void batchUpdate(List<ProductDto> productDtos) {
        List<Product> products = productDtos.stream().map(productMapper::productDtoToProduct).toList();
        productRepository.batchUpdate(products);
    }

    public void batchInsert(List<ProductDto> productDtos) {
        List<Product> products = productDtos.stream().map(productMapper::productDtoToProduct).toList();
        productRepository.batchInsert(products);
    }




}
