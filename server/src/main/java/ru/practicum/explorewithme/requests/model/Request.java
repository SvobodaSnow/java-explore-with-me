package ru.practicum.explorewithme.requests.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import ru.practicum.explorewithme.users.model.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "requests", schema = "public")
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private Long eventId;
    @ManyToOne
    @JoinColumn(name = "request_participant_id")
    private User requester;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Column
    private LocalDateTime created;
}
