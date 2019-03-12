package com.forum.publicforum.req;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.forum.publicforum.constant.ValidationConstant;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest extends BaseRequest{

    @Size(min = 4, max = 30, message = ValidationConstant.EMAIL_SIZE_VALIDATION)
    @NotEmpty(message = ValidationConstant.EMAIL_NOT_EMPTY)
    @NotNull(message = ValidationConstant.EMAIL_NOT_NULL)
    @Email
    private String email;

    @Size(min = 5, max = 16, message = ValidationConstant.PASSWORD_SIZE_VALIDATION)
    @NotEmpty(message = ValidationConstant.PASSWORD_NOT_EMPTY)
    @NotNull(message = ValidationConstant.PASSWORD_NOT_NULL)
    private String password;
}
