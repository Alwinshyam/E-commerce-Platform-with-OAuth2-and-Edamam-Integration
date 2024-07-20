package com.example.demo.service;

import com.example.demo.model.Category;
import com.example.demo.repo.CategoriesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategorieService {

    @Autowired
    private CategoriesRepo categorieRepo;

    public List<Category> getAllCategories() {
        return categorieRepo.findAll();
    }

    public void addCategories(Category category) {
        categorieRepo.save(category);
    }

    public void removeCategoriesById(int id) {
        categorieRepo.deleteById(id);
    }

    public Optional<Category> getcategoryById(int id) {
        return categorieRepo.findById(id);
    }
}
