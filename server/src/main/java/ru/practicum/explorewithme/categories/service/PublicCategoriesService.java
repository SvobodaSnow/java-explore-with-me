package ru.practicum.explorewithme.categories.service;

import ru.practicum.explorewithme.categories.model.Categories;

import java.util.List;

public interface PublicCategoriesService {
    List<Categories> getCategories(int from, int size);

    Categories getCategoryById(Long catId);
}
