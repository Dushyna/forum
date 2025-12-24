package ait.cohort70.accounting.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UserRegisterDto {
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9_]*$", message = "Имя может содержать только буквы, цифры и нижнее подчеркивание")
    @Size(min = 3, max = 20, message ="Логин должен иметь от 3 до 20 символов")
    private String login;
    @NotBlank(message = "Пароль обязателен")
//    @Pattern(
//            regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}$",
//            message = "Пароль должен содержать минимум одну цифру, заглавную и строчную буквы, спецсимвол 8 символов  и быть без пробелов"
//    )
    private String password;
    @NotBlank(message = "Имя обязательно")
    @Size(min = 2, max = 20, message ="Имя должно иметь от 2 до 20 символов")
    private String firstName;
    @NotBlank(message = "Фамилия обязательна")
    @Size(min = 2, max = 20, message ="Имя должно иметь от 2 до 20 символов")
    private String lastName;
}
