package ru.practicum.explorewithme.categories.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.practicum.explorewithme.categories.model.Categories;
import ru.practicum.explorewithme.categories.storage.CategoriesStorage;

import java.util.List;

@Service
@Primary
public class PublicCategoriesServiceImp implements PublicCategoriesService {
    @Autowired
    private CategoriesStorage categoriesStorage;

    @Override
    public List<Categories> getCategories(int from, int size) {
        int page = from / size;
        return categoriesStorage.findAll(PageRequest.of(page, size)).getContent();
    }

    @Override
    public Categories getCategoryById(Long catId) {
        Categories categories = categoriesStorage.getById(catId);
        String name = categories.getName();
        return categories;
    }
}
