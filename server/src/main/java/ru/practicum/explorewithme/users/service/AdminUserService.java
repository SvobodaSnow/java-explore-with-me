package ru.practicum.explorewithme.users.service;

import ru.practicum.explorewithme.users.dto.UserDto;

import java.util.List;

public interface AdminUserService {
    UserDto createNewUser(UserDto newUserDto);

    List<UserDto> getUsers(List<Long> ids, int from, int size);

    void deleteUserById(Long userId);
}
