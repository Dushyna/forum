package ait.cohort70.accounting.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class NewRegisterDto {
    private String login;
    private String password;
    private String firstName;
    private String lastName;
}
