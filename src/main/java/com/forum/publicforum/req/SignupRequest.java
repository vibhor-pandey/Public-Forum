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
public class SignupRequest extends BaseRequest{
    
    @NotNull(message = ValidationConstant.NAME_NOT_NULL)
    @NotEmpty(message = ValidationConstant.NAME_NOT_EMPTY)
    @Size(min = 3, message = ValidationConstant.NAME_SIZE_VALIDATION)
    private String name;
    
    @NotNull(message = ValidationConstant.PASSWORD_NOT_NULL)
    @NotEmpty(message = ValidationConstant.PASSWORD_NOT_EMPTY)
    @Size(min = 5, max = 16, message = ValidationConstant.PASSWORD_SIZE_VALIDATION)
    private String password;
    
    @NotNull(message = ValidationConstant.EMAIL_NOT_EMPTY)
    @NotEmpty(message = ValidationConstant.EMAIL_NOT_EMPTY)
    @Size(min = 4, message = ValidationConstant.EMAIL_SIZE_VALIDATION)
    @Email
    private String email;
}
