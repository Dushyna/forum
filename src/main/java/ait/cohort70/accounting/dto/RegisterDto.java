package ait.cohort70.accounting.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Singular;

import java.util.Set;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDto {
    private String login;
    private String firstName;
    private String lastName;
    @Singular
    private Set<Roles> roles;

}
