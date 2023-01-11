package ru.practicum.explorewithme.categories.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import ru.practicum.explorewithme.categories.model.Categories;
import ru.practicum.explorewithme.categories.storage.CategoriesStorage;

@Primary
@Service
public class AdminCategoriesServiceImp implements AdminCategoriesService {
    @Autowired
    private CategoriesStorage categoriesStorage;

    @Override
    public Categories createNewCategories(Categories categories) {
        return categoriesStorage.save(categories);
    }

    @Override
    public Categories updateCategories(Categories categories) {
        return categoriesStorage.save(categories);
    }

    @Override
    public void deleteCategories(Long categoriesId) {
        categoriesStorage.deleteById(categoriesId);
    }
}
