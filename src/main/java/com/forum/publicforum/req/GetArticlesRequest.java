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
public class GetArticlesRequest extends BaseRequest {
    
    @Email
    @NotEmpty(message = ValidationConstant.EMAIL_NOT_EMPTY)
    @NotNull(message = ValidationConstant.EMAIL_NOT_NULL)
    @Size(min = 4, max = 30, message = ValidationConstant.EMAIL_SIZE_VALIDATION)
    private String email;
    
    private boolean ownArticles;
}
