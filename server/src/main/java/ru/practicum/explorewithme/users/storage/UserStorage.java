package ru.practicum.explorewithme.users.storage;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.explorewithme.users.model.User;

public interface UserStorage extends JpaRepository<User, Long> {
}
