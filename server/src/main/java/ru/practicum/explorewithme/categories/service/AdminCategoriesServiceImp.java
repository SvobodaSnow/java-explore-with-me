package ru.practicum.explorewithme.categories.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import ru.practicum.explorewithme.categories.model.Categories;
import ru.practicum.explorewithme.categories.storage.CategoriesStorage;
import ru.practicum.explorewithme.exceptions.model.ValidationException;

import java.time.LocalDateTime;

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
        if (categories.getName() == null || categories.getName().isEmpty()) {
            throw new ValidationException(
                    "Не указано новое категории",
                    "Не передано имя категории",
                    LocalDateTime.now()
            );
        }
        if (categories.getId() == null) {
            throw new ValidationException(
                    "Не указан ID категории",
                    "Не указан ID категории",
                    LocalDateTime.now()
            );
        }
        return categoriesStorage.save(categories);
    }

    @Override
    public void deleteCategories(Long categoriesId) {
        categoriesStorage.deleteById(categoriesId);
    }
}
