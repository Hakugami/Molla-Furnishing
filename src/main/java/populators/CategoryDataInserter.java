package populators;

import models.entity.Category;
import models.entity.SubCategory;
import persistence.manager.DatabaseSingleton;
import persistence.repositories.impl.CategoriesRepository;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CategoryDataInserter {

    private final CategoriesRepository categoryRepository;


    public CategoryDataInserter() {
        this.categoryRepository = new CategoriesRepository();

    }

    public static void main(String[] args) {
        CategoryDataInserter categoryDataInserter = new CategoryDataInserter();
        categoryDataInserter.insertCategoriesAndSubCategories();
    }

    public void insertCategoriesAndSubCategories() {
        Map<String, List<String>> categoriesAndSubCategories = new HashMap<>();
        categoriesAndSubCategories.put("Chairs", Arrays.asList("Armchairs", "Sofas", "Benches", "Stools"));
        categoriesAndSubCategories.put("Tables", Arrays.asList("Dining Tables", "Coffee Tables", "Side Tables", "Bedside Tables"));
        categoriesAndSubCategories.put("Storage", Arrays.asList("Wardrobes", "Cabinets", "Dressers", "Bookcases", "Shelving Units"));
        categoriesAndSubCategories.put("Beds", Arrays.asList("Bed Frames", "Mattresses", "Nightstands", "Bunk Beds"));
        categoriesAndSubCategories.put("Desks", Arrays.asList("Writing Desks", "Computer Desks", "Office Chairs", "Filing Cabinets"));
        categoriesAndSubCategories.put("Dining", Arrays.asList("Dining Sets", "Buffets"));
        categoriesAndSubCategories.put("Children", Arrays.asList("Cribs", "Kid Beds", "Study Desks"));
        DatabaseSingleton.getInstance().doTransaction(entityManager -> {
            for (Map.Entry<String, List<String>> entry : categoriesAndSubCategories.entrySet()) {
                Category category = new Category();
                category.setName(entry.getKey());
                categoryRepository.create(category, entityManager);
            }
        });
        DatabaseSingleton.getInstance().doTransaction(entityManager -> {
            for (Map.Entry<String, List<String>> entry : categoriesAndSubCategories.entrySet()) {
                Category category = categoryRepository.getCategoryByName(entry.getKey(), entityManager).orElse(null);
                if (category != null) {
                    for (String subCategoryName : entry.getValue()) {
                        SubCategory subCategory = new SubCategory();
                        subCategory.setName(subCategoryName);
                        category.addSubCategory(subCategory);
                    }
                    categoryRepository.update(category, entityManager);
                }
            }
        });
    }
}