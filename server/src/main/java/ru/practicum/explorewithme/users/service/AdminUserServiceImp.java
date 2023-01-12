package ru.practicum.explorewithme.users.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.practicum.explorewithme.exceptions.model.ValidationException;
import ru.practicum.explorewithme.users.dto.UserDto;
import ru.practicum.explorewithme.users.dto.UserMapper;
import ru.practicum.explorewithme.users.model.User;
import ru.practicum.explorewithme.users.storage.UserStorage;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Primary
@Service
public class AdminUserServiceImp implements AdminUserService {
    @Autowired
    private UserStorage userStorage;

    @Override
    public UserDto createNewUser(UserDto newUserDto) {
        try {
            User newUser = UserMapper.toUser(newUserDto);
            return UserMapper.toUserDto(userStorage.save(newUser));
        } catch (DataIntegrityViolationException e) {
            throw new ValidationException(
                    "Не верно оказаны данные пользователя",
                    "Не указаны данные пользователя",
                    LocalDateTime.now()
            );
        }
    }

    @Override
    public List<UserDto> getUsers(List<Long> ids, int from, int size) {
        Iterable<User> users;
        int page = from / size;
        if (ids != null) {
            users = userStorage.findAllById(ids);
        } else {
            users = userStorage.findAll(PageRequest.of(page, size));
        }
        List<UserDto> userDtoList = new ArrayList<>();
        for (User user : users) {
            userDtoList.add(UserMapper.toUserDto(user));
        }
        return userDtoList;
    }

    @Override
    public void deleteUserById(Long userId) {
        userStorage.deleteById(userId);
    }
}
