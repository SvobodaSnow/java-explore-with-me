package ru.practicum.explorewithme.users.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explorewithme.events.service.PrivateEventService;
import ru.practicum.explorewithme.users.service.PrivateUserService;

@Slf4j
@Validated
@RestController
@RequestMapping(path = "/users")
public class PrivateUserController {
    @Autowired
    private PrivateUserService privateUserService;
    @Autowired
    private PrivateEventService privateEventService;
}
