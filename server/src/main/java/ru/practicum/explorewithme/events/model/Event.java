package ru.practicum.explorewithme.events.model;

import lombok.*;
import ru.practicum.explorewithme.categories.model.Categories;
import ru.practicum.explorewithme.users.model.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "events", schema = "public")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String annotation;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Categories category;
    @Column
    private int confirmedRequests;
    @Column
    private LocalDateTime createdOn;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private LocalDateTime eventDate;
    @ManyToOne
    @JoinColumn(name = "initiator_id")
    private User initiator;
    @OneToOne
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;
    @Column(nullable = false)
    private boolean paid;
    @Column(nullable = false)
    private int participantLimit;
    @Column
    private boolean available;
    @Column
    private LocalDateTime publishedOn;
    @Column(nullable = false)
    private boolean requestModeration;
    @Enumerated(EnumType.STRING)
    private State state;
    @Column(nullable = false)
    private String title;
    @Column
    private Long views;
}
