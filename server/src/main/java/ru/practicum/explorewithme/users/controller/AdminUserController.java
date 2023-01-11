package ru.practicum.explorewithme.users.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explorewithme.users.dto.UserDto;
import ru.practicum.explorewithme.users.service.AdminUserService;

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
            @RequestParam(defaultValue = "0") int from,
            @RequestParam(defaultValue = "10") int size
    ) {
        log.info("Получен запрос на формирования списка пользоветелей с ID: " + ids + ". Начиная с " + from +
                " пользователя. Количество пользователей на странице: " + size);
        return adminUserService.getUsers(ids, from, size);
    }

    @DeleteMapping("/{userId}")
    public boolean deleteUserById(@PathVariable Long userId) {
        log.info("Получен запрос на удаление пользователя с ID: " + userId);
        adminUserService.deleteUserById(userId);
        return true;
    }
}
