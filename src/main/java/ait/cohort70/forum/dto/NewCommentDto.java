package ait.cohort70.forum.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class NewCommentDto {
    @NotBlank(message = "Comment cannot be empty")
    @Size(min = 5, message = "Comment must be between 5 and 100 characters")
    private String message;
}
