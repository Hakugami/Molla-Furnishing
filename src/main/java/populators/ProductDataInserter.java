//package populators;
//
//import models.entity.Category;
//import models.entity.Product;
//import persistence.repositories.impl.CategoriesRepository;
//import persistence.repositories.impl.ProductRepository;
//
//import java.util.List;
//
//public class ProductDataInserter {
//
//    private final ProductRepository productRepository;
//    private final CategoriesRepository categoryRepository;
//
//    public ProductDataInserter() {
//        this.productRepository = new ProductRepository();
//        this.categoryRepository = new CategoriesRepository();
//    }
//
//    public void updateProductCategories() {
//        // Create the categories
//        Category category1 = new Category();
//        category1.setName("Category 1");
//
//        Category category2 = new Category();
//        category2.setName("Category 2");
//
//        // Save the categories to the database
//        categoryRepository.create(category1);
//        categoryRepository.create(category2);
//
//        // Retrieve all the products
//        List<Product> products = productRepository.retrieveProducts(1, Integer.MAX_VALUE);
//
//        // Assign the categories to the products
//        for (int i = 0; i < products.size(); i++) {
//            Product product = products.get(i);
//
//            if (i % 2 == 0) {
//                product.setCategory(category1);
//            } else {
//                product.setCategory(category2);
//            }
//        }
//
//        // Update the products in the database
//        productRepository.batchUpdate(products);
//    }
//
//    public static void main(String[] args) {
//        ProductDataInserter productDataInserter = new ProductDataInserter();
//        productDataInserter.updateProductCategories();
//    }
//}