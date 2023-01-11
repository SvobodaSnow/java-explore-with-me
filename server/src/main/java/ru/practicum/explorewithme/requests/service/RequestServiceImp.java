package ru.practicum.explorewithme.requests.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import ru.practicum.explorewithme.events.model.Event;
import ru.practicum.explorewithme.events.storage.EventStorage;
import ru.practicum.explorewithme.requests.dto.RequestDto;
import ru.practicum.explorewithme.requests.dto.RequestMapper;
import ru.practicum.explorewithme.requests.model.Request;
import ru.practicum.explorewithme.requests.model.Status;
import ru.practicum.explorewithme.requests.storage.RequestStorage;
import ru.practicum.explorewithme.users.model.User;
import ru.practicum.explorewithme.users.storage.UserStorage;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Primary
@Service
public class RequestServiceImp implements RequestService {
    @Autowired
    private RequestStorage requestStorage;
    @Autowired
    private UserStorage userStorage;
    @Autowired
    private EventStorage eventStorage;

    @Override
    public RequestDto createNewRequest(Long requesterId, Long eventId) {
        Request newRequest = new Request();
        User requester = userStorage.getById(requesterId);
        Event event = eventStorage.getById(eventId);
        newRequest.setRequester(requester);
        newRequest.setEventId(eventId);
        newRequest.setCreated(LocalDateTime.now());
        if (event.isRequestModeration()) {
            newRequest.setStatus(Status.PENDING);
        } else {
            newRequest.setStatus(Status.CONFIRMED);
        }
        return RequestMapper.toRequestDto(requestStorage.save(newRequest));
    }

    @Override
    public List<RequestDto> getAllRequestsForUser(Long requesterId) {
        List<Request> requests = requestStorage.findByRequester_Id(requesterId);
        List<RequestDto> requestDtoList = new ArrayList<>();
        for (Request request : requests) {
            requestDtoList.add(RequestMapper.toRequestDto(request));
        }
        return requestDtoList;
    }

    @Override
    public RequestDto cancelRequest(Long userId, Long eventId) {
        Request request = requestStorage.findByRequester_IdAndEventId(userId, eventId);
        request.setStatus(Status.CANCELED);
        return RequestMapper.toRequestDto(requestStorage.save(request));
    }

    @Override
    public List<RequestDto> getAllRequestsForEvent(Long userId, Long eventId) {
        List<Request> requests = requestStorage.findByEventId(eventId);
        List<RequestDto> requestDtoList = new ArrayList<>();
        for (Request request : requests) {
            requestDtoList.add(RequestMapper.toRequestDto(request));
        }
        return requestDtoList;
    }

    @Override
    public RequestDto confirmRequest(@PathVariable Long userId, @PathVariable Long eventId, @PathVariable Long reqId) {
        Request request = requestStorage.getById(reqId);
        request.setStatus(Status.CONFIRMED);
        return RequestMapper.toRequestDto(requestStorage.save(request));
    }

    @Override
    public RequestDto rejectRequest(@PathVariable Long userId, @PathVariable Long eventId, @PathVariable Long reqId) {
        Request request = requestStorage.getById(reqId);
        request.setStatus(Status.REJECTED);
        return RequestMapper.toRequestDto(requestStorage.save(request));
    }
}
