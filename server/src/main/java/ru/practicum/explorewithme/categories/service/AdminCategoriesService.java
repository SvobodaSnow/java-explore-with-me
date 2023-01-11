package ru.practicum.explorewithme.categories.service;

import ru.practicum.explorewithme.categories.model.Categories;

public interface AdminCategoriesService {
    Categories createNewCategories(Categories categories);

    Categories updateCategories(Categories categories);

    void deleteCategories(Long categoriesId);
}
