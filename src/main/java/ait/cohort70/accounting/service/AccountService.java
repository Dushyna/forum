package ait.cohort70.accounting.service;

import ait.cohort70.accounting.dto.*;


public interface AccountService {
    RegisterDto registerUser(NewRegisterDto newRegisterDto);

    RegisterDto loginUser(String userName);

    RegisterDto deleteUser(String userName);

    RegisterDto updateUser(String userName, UserUpdateDto userUpdateDto);

    RoleDto addRole(String userName, String role);

    RoleDto deleteRole(String userName, String role);

    void changePassword(String userName, PasswordDto passwordDto);

    RegisterDto getUser(String userName);


}
