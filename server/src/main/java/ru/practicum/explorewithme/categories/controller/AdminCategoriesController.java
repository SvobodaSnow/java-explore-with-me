package ru.practicum.explorewithme.categories.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explorewithme.categories.model.Categories;
import ru.practicum.explorewithme.categories.service.AdminCategoriesService;

@Slf4j
@Validated
@RestController
@RequestMapping(path = "/admin/categories")
public class AdminCategoriesController {
    @Autowired
    private AdminCategoriesService adminCategoriesService;

    @PostMapping
    public Categories createNewCategories(@RequestBody Categories categories) {
        log.info("Получен запрос на создание новой категории. Название категории: " + categories.getName());
        return adminCategoriesService.createNewCategories(categories);
    }

    @PatchMapping
    public Categories updateCategories(@RequestBody Categories categories) {
        log.info("Получен запрос на обновление категории. ID категории: " + categories.getId() +
                ". Название категории " + categories.getName());
        return adminCategoriesService.updateCategories(categories);
    }

    @DeleteMapping("/{catId}")
    public boolean deleteCategories(@PathVariable Long catId) {
        log.info("Получен запрос на удаление категории. ID категории: " + catId);
        adminCategoriesService.deleteCategories(catId);
        return true;
    }
}
