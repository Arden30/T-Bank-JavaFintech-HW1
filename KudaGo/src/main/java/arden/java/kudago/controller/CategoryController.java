package arden.java.kudago.controller;

import arden.java.kudago.dto.Category;
import arden.java.kudago.service.impl.CategoryServiceImpl;
import configuration.annotation.logtimexec.LogTimeExec;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/places/categories")
@RequiredArgsConstructor
@LogTimeExec
public class CategoryController {
    private final CategoryServiceImpl categoryServiceImpl;

    @GetMapping
    public ResponseEntity<List<Category>> getCategories() {
        return ResponseEntity.ok(categoryServiceImpl.getAllCategories());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategory(@PathVariable Long id) {
        return ResponseEntity.ok(categoryServiceImpl.getCategoryById(id));
    }

    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        return ResponseEntity.ok(categoryServiceImpl.createCategory(category));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody Category category) {
        return ResponseEntity.ok(categoryServiceImpl.updateCategory(id, category));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteCategory(@PathVariable Long id) {
        return ResponseEntity.ok(categoryServiceImpl.deleteCategory(id));
    }
}
