package ru.practicum.explorewithme.categories.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explorewithme.categories.model.Categories;
import ru.practicum.explorewithme.categories.service.AdminCategoriesService;

import javax.validation.constraints.Positive;
import java.text.MessageFormat;

@Slf4j
@Validated
@RestController
@RequestMapping(path = "/admin/categories")
public class AdminCategoriesController {
    @Autowired
    private AdminCategoriesService adminCategoriesService;

    @PostMapping
    public Categories createNewCategories(@RequestBody Categories categories) {
        log.info(
                MessageFormat.format(
                        "Получен запрос на создание новой категории. Название категории: {0}",
                        categories.getName()
                )
        );
        return adminCategoriesService.createNewCategories(categories);
    }

    @PatchMapping
    public Categories updateCategories(@RequestBody Categories categories) {
        log.info(
                MessageFormat.format(
                        "Получен запрос на обновление категории. ID категории: {0}. Название категории {1}",
                        categories.getId(),
                        categories.getName()
                )
        );
        return adminCategoriesService.updateCategories(categories);
    }

    @DeleteMapping("/{catId}")
    public boolean deleteCategories(@Positive @PathVariable Long catId) {
        log.info(MessageFormat.format("Получен запрос на удаление категории. ID категории: {0}", catId));
        adminCategoriesService.deleteCategories(catId);
        return true;
    }
}
