package com.forum.publicforum.service;

import com.forum.publicforum.res.BaseResponse;
import com.forum.publicforum.util.ErrorCode;

public class BaseService {
    
    
    protected BaseResponse getErrorResponse(ErrorCode code, BaseResponse response) {
        response.setError(code.getCode());
        response.setErrorCode(code);
        response.setDescription(code.getDescription());
        response.setSuccess(false);
        return response;
    }
}
