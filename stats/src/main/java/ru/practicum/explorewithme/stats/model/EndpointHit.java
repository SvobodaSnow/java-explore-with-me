package ru.practicum.explorewithme.stats.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

import static ru.practicum.explorewithme.stats.model.GlobalVariables.PATTERN_DATE_TIME;

@Entity
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "endpoint_hit", schema = "public")
public class EndpointHit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String app;
    @Column(nullable = false)
    private String uri;
    @Column(nullable = false)
    private String ip;
    @Column(name = "created_on", nullable = false)
    @JsonFormat(pattern = PATTERN_DATE_TIME)
    private LocalDateTime timestamp;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        EndpointHit that = (EndpointHit) o;
        return Objects.equals(app, that.app) &&
                Objects.equals(uri, that.uri) &&
                Objects.equals(ip, that.ip);
    }

    @Override
    public int hashCode() {
        return Objects.hash(app, uri, ip, timestamp);
    }
}
