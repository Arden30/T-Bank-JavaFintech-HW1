package arden.java.kudago.service.impl;

import arden.java.kudago.dto.Category;
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
    private final StorageRepository<Long, Category> categoryRepository;

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.readAll();
    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.read(id);
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
