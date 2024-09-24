package arden.java.kudago.service.impl;

import arden.java.kudago.client.CategoryRestTemplate;
import arden.java.kudago.dto.Category;
import arden.java.kudago.exception.GeneralException;
import arden.java.kudago.repository.StorageRepository;
import arden.java.kudago.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRestTemplate categoryRestTemplate;
    private final StorageRepository<Long, Category> categoryRepository;

    @Override
    public List<Category> getAllCategories() {
        if (categoryRestTemplate.getAllCategories().isPresent()) {
            List<Category> categories = categoryRestTemplate.getAllCategories().get();
            categories.forEach(category -> categoryRepository.create(category.id(), category));
            log.info("All categories are saved");

            return categories;
        }

        log.info("Problems with saving categories");
        throw new GeneralException("Categories were not found");
    }

    @Override
    public Category getCategoryById(Long id) {
        if (categoryRestTemplate.getCategory(id).isPresent()) {
            Category category = categoryRestTemplate.getCategory(id).get();
            log.info("Category with id {} is found", id);

            return categoryRepository.create(category.id(), category);
        }

        log.error("Category with id {} is not found", id);
        throw new GeneralException("Category with id " + id + " was not found");
    }

    @Override
    public Category createCategory(Category category) {
        return categoryRepository.create(category.id(), category);
    }

    @Override
    public Category updateCategory(Long id, Category category) {
        return categoryRepository.update(id, category);
    }

    @Override
    public boolean deleteCategory(Long id) {
        return categoryRepository.delete(id);
    }
}
