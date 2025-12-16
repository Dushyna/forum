package ait.cohort70.accounting.controller;

import ait.cohort70.accounting.dto.*;
import ait.cohort70.accounting.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
public class AccountController {
    private final AccountService accountService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public RegisterDto registerUser(@RequestBody NewRegisterDto newRegisterDto) {
        return accountService.registerUser(newRegisterDto);

    }

    @PostMapping("/login")
    public RegisterDto loginUser(Principal principal) {
        return accountService.loginUser(principal.getName());
    }

    @DeleteMapping("/user/{userName}")
    @PreAuthorize("#userName == principal.name or hasRole('ADMIN')")
    public RegisterDto deleteUser(@PathVariable String userName) {
        return accountService.deleteUser(userName);
    }


    @PatchMapping("/user/{userName}")
    @PreAuthorize("#userName == principal.name")
    public RegisterDto updateUser(@PathVariable String userName, @RequestBody UserUpdateDto userUpdateDto) {
        return accountService.updateUser(userName, userUpdateDto);
    }

    @PatchMapping("/user/{userName}/role/{role}")
    @PreAuthorize("hasRole('ADMIN')")
    public RoleDto addRole(@PathVariable String userName, @PathVariable String  role) {
        return accountService.addRole(userName, role);
    }

    @DeleteMapping("/user/{userName}/role/{role}")
    @PreAuthorize("hasRole('ADMIN')")
    public RoleDto deleteRole(@PathVariable String userName, @PathVariable String role) {
        return accountService.deleteRole(userName, role);
    }

    @PatchMapping("/password")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void changePassword(Principal principal, @RequestBody PasswordDto passwordDto) {
         accountService.changePassword(principal.getName(), passwordDto);
    }

    @GetMapping("/user/{userName}")
    @PreAuthorize("hasRole('ADMIN')or #userName == principal.name")
    public RegisterDto getUser(@PathVariable String userName) {
        return accountService.getUser(userName);
    }
}
