package com.forum.publicforum.res;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse extends BaseResponse{
    
    private String auth_token;
}
