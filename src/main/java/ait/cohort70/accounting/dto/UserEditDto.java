package ait.cohort70.accounting.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UserEditDto {
    @Size(min = 2, max = 20, message ="Имя должно иметь от 2 до 20 символов")
    private String firstName;
    @Size(min = 2, max = 20, message ="Имя должно иметь от 2 до 20 символов")
    private String lastName;
}
