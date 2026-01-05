package ait.cohort70.accounting.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class EmailDto {
    @NotBlank(message = "To field is required")
    @Email(message = "To field must be a valid email adres")
    private String to;
    @NotBlank(message = "Subject field is required")
    private String subject;
    @NotBlank(message = "Message field is required")
     private String message;
}
