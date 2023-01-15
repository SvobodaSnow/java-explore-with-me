package ru.practicum.explorewithme.users.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explorewithme.users.dto.UserDto;
import ru.practicum.explorewithme.users.service.AdminUserService;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.text.MessageFormat;
import java.util.List;

@Slf4j
@Validated
@RestController
@RequestMapping(path = "/admin/users")
public class AdminUserController {
    @Autowired
    private AdminUserService adminUserService;

    @PostMapping
    public UserDto createNewUser(@RequestBody UserDto newUserDto) {
        log.info("Получен запрос на добавление навого пользователя");
        return adminUserService.createNewUser(newUserDto);
    }

    @GetMapping
    public List<UserDto> getUsers(
            @RequestParam(required = false) List<Long> ids,
            @PositiveOrZero @RequestParam(defaultValue = "0") int from,
            @Positive @RequestParam(defaultValue = "10") int size
    ) {
        log.info(
                MessageFormat.format(
                        "Получен запрос на формирования списка пользоветелей с ID: {0}. " +
                                "Начиная с {1} пользователя. Количество пользователей на странице: {2}",
                        ids,
                        from,
                        size
                )
        );
        return adminUserService.getUsers(ids, from, size);
    }

    @DeleteMapping("/{userId}")
    public boolean deleteUserById(@Positive @PathVariable Long userId) {
        log.info(MessageFormat.format("Получен запрос на удаление пользователя с ID: {0}", userId));
        adminUserService.deleteUserById(userId);
        return true;
    }
}
