package ru.practicum.explorewithme.categories.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explorewithme.categories.model.Categories;
import ru.practicum.explorewithme.categories.service.PublicCategoriesService;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.text.MessageFormat;
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
            @PositiveOrZero @RequestParam(defaultValue = "0") int from,
            @Positive @RequestParam(defaultValue = "10") int size
    ) {
        log.info("Получен запрос на формирование списка категорий");
        return publicCategoriesService.getCategories(from, size);
    }

    @GetMapping("/{catId}")
    public Categories getCategoryById(@Positive @PathVariable Long catId) {
        log.info(MessageFormat.format("Получен запрос на отправку категории с ID: {0}", catId));
        return publicCategoriesService.getCategoryById(catId);
    }
}
