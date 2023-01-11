package ru.practicum.explorewithme.categories.storage;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.explorewithme.categories.model.Categories;

public interface CategoriesStorage extends JpaRepository<Categories, Long> {
}
