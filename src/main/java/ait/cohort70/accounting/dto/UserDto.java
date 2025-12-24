package ait.cohort70.accounting.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Set;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9_]*$", message = "Имя может содержать только буквы, цифры и нижнее подчеркивание")
    @Size(min = 3, max = 20)
    private String login;
    private String firstName;
    private String lastName;
    @Singular
    private Set<String> roles;
}
