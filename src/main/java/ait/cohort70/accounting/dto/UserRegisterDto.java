package ait.cohort70.accounting.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UserRegisterDto {
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9_]*$", message = "Имя может содержать только буквы, цифры и нижнее подчеркивание")
    @Size(min = 3, max = 20)
    private String login;
    @NotBlank(message = "Пароль обязателен")
    @Pattern(
            regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}$",
            message = "Пароль должен содержать минимум одну цифру, заглавную и строчную буквы, спецсимвол и быть без пробелов"
    )
    private String password;
    private String firstName;
    private String lastName;
}
