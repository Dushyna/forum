package ait.cohort70.forum.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class NewPostDto {
    @NotBlank(message = "Title cannot be empty")
    @Size(min = 3, max = 100, message = "Title must be between 5 and 100 characters")
    private String title;
    @NotBlank(message = "Content cannot be empty")
    @Size(min = 10, message = "Content must be between 5 and 100 characters")
    private String content;
    private Set<String> tags;
}
