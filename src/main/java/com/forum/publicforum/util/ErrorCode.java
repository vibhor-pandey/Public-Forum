package com.forum.publicforum.util;

import com.forum.publicforum.constant.ErrorConstant;
import com.forum.publicforum.constant.MessageConstant;

public enum ErrorCode {

    USER_NOT_EXIST(101, MessageConstant.LOGIN_USER_NOT_EXIST),
    WRONG_CREDENTIALS(102, MessageConstant.LOGIN_WRONG_CREDENTIALS),
    USER_ALREADY_EXIST(104, MessageConstant.EMAIL_USER_ALREADY_EXISTS),
    ARTICLE_NOT_EXIST(105, MessageConstant.ARTICLE_NOT_EXIST),
    USER_NOT_AUTHENTICATED(106, MessageConstant.USER_NOT_AUTHENTICATED),
    
    UNKOWN_ERROR(501, ErrorConstant.UNKONW_ERROR);
    
    

    private final int code;
    private final String description;

    private ErrorCode(int code, String description) {
      this.code = code;
      this.description = description;
    }

    public String getDescription() {
       return description;
    }

    public int getCode() {
       return code;
    }

    @Override
    public String toString() {
      return code + ": " + description;
    }
}
