package com.forum.publicforum.req;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.forum.publicforum.constant.ValidationConstant;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthRequest extends BaseRequest{
    
    @NotNull(message = ValidationConstant.AUTH_TOKEN_NOT_NULL)
    @NotEmpty(message = ValidationConstant.AUTH_TOKEN_NOT_EMPTY)
    private String auth_token;
    
}
