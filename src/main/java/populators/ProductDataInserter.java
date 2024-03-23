package populators;

import models.entity.Category;
import models.entity.Product;
import models.entity.ProductDetails;
import models.entity.SubCategory;
import persistence.repositories.impl.CategoriesRepository;
import persistence.repositories.impl.ProductRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.Stack;
import java.util.Arrays;
import java.util.Collections;

public class ProductDataInserter {

    private final ProductRepository productRepository;
    private final CategoriesRepository categoryRepository;
    private Set<String> generatedNames = new HashSet<>();

    public ProductDataInserter() {
        this.productRepository = new ProductRepository();
        this.categoryRepository = new CategoriesRepository();
    }

    //insert like 100 products
    public void insertProducts() {
        List<Category> categories = categoryRepository.retrieveCategories(1, Integer.MAX_VALUE);

        int productCount = 0;

        for (Category category : categories) {
            List<SubCategory> subCategories = category.getSubCategories();

            for (SubCategory subCategory : subCategories) {

                // Generate a name for the product based on category and subcategory
                String productName = generateRealisticProductNameDFS(category.getName(), subCategory.getName());

                Product product = new Product();
                product.setName(productName);
                product.setDescription("Description " + productCount);
                product.setPrice(10.0 * productCount);
                product.setQuantity(10 * productCount);
                product.setDateAdded(new java.util.Date(System.currentTimeMillis() - (long) (Math.random() * 1000000000)));

                product.setCategory(category);
                product.setSubCategory(subCategory);
                productRepository.create(product);
                productCount++;
            }
        }
    }

    private String generateRealisticProductNameDFS(String categoryName, String subCategoryName) {
        // Sample adjectives and nouns for generating more realistic product names
        String[] adjectives = {"Elegant", "Modern", "Vintage", "Luxury", "Sleek", "Rustic"};
        String[] nouns = {"Chair", "Table", "Sofa", "Bed", "Desk", "Wardrobe"};

        // Shuffle the arrays to ensure randomness in selection
        shuffleArray(adjectives);
        shuffleArray(nouns);

        Stack<String> stack = new Stack<>();
        stack.push("");

        // Perform DFS to generate unique combinations
        while (!stack.isEmpty()) {
            String currentCombination = stack.pop();

            // Construct the product name
            String productName = currentCombination.trim() + " " + categoryName + " " + subCategoryName + " " ;

            // Check if the generated name is unique
            if (!generatedNames.contains(productName)) {
                generatedNames.add(productName);
                return productName;
            }

            // Explore next level combinations
            for (String adjective : adjectives) {
                stack.push(currentCombination + " " + adjective);
            }
        }

        // If all combinations are exhausted, return a default name
        return "Product " + categoryName + " " + subCategoryName;
    }



    public void addProductDetailsToProducts() {
        // Create a ProductDetails object
        ProductDetails productDetails = new ProductDetails();
        productDetails.setMaterial("Wood");
        productDetails.setDimensions("200x200x200");
        productDetails.setColor("Brown");
        productDetails.setWeight("20kg");

        // Retrieve all the products
        List<Product> products = productRepository.retrieveProducts(1, Integer.MAX_VALUE);

        // For each product, set the ProductDetails and update the product in the database
        for (Product product : products) {
            product.setProductDetails(productDetails);
        }
        productRepository.batchUpdate(products);
    }

    private void shuffleArray(String[] array) {
    List<String> list = Arrays.asList(array);
    Collections.shuffle(list);
    list.toArray(array);
}


    public void addImagesToProducts() {
        // Retrieve all the products
        List<Product> products = productRepository.retrieveProducts(1, Integer.MAX_VALUE);
        products.forEach(product -> {
            product.getImages().add("images/product/electronic/product2.jpg");
            product.getImages().add("images/product/product-d-1.jpg");
        });

        // Update the products in the database
        productRepository.batchUpdate(products);
    }


    public static void main(String[] args) {
        ProductDataInserter productDataInserter = new ProductDataInserter();
        productDataInserter.insertProducts();
        productDataInserter.addImagesToProducts();
        productDataInserter.addProductDetailsToProducts();

    }
}