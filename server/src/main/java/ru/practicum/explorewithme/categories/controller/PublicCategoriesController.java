package ru.practicum.explorewithme.categories.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explorewithme.categories.model.Categories;
import ru.practicum.explorewithme.categories.service.PublicCategoriesService;

import java.util.List;

@Slf4j
@Validated
@RestController
@RequestMapping(path = "/categories")
public class PublicCategoriesController {
    @Autowired
    private PublicCategoriesService publicCategoriesService;

    @GetMapping
    public List<Categories> getCategories(
            @RequestParam(defaultValue = "0") int from,
            @RequestParam(defaultValue = "10") int size
    ) {
        log.info("Получен запрос на формирование списка категорий");
        return publicCategoriesService.getCategories(from, size);
    }

    @GetMapping("/{catId}")
    public Categories getCategoryById(@PathVariable Long catId) {
        log.info("Получен запрос на отправку категории с ID " + catId);
        return publicCategoriesService.getCategoryById(catId);
    }
}
