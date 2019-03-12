package com.forum.publicforum.res;

import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.forum.publicforum.util.ErrorCode;

@Getter
@Setter
@JsonInclude(value = Include.NON_NULL)
public class BaseResponse {
    
    protected ErrorCode errorCode;
    
    private boolean isSuccess;
    
    private String message;
}
