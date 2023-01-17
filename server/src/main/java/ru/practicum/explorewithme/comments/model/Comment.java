package ru.practicum.explorewithme.comments.model;

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
@Table(name = "comments", schema = "public")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private User author;
    @Column(name = "event_id", nullable = false)
    private Long eventId;
    private String text;
    private LocalDateTime created;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;
}
