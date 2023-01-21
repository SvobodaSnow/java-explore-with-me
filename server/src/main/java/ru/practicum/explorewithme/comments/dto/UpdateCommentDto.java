package ru.practicum.explorewithme.comments.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCommentDto {
    private Long id;
    private String text;
}
