package com.forum.publicforum.req;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.URL;

import lombok.Getter;
import lombok.Setter;

import com.forum.publicforum.constant.ValidationConstant;

@Getter
@Setter
public class PostArticleRequest extends AuthRequest {
    
    @NotNull(message = ValidationConstant.EMAIL_NOT_NULL)
    @NotEmpty(message = ValidationConstant.EMAIL_NOT_EMPTY)
    @Size(min = 4, max = 30, message = ValidationConstant.EMAIL_SIZE_VALIDATION)
    @Email
    private String email;
    
    @NotNull(message = ValidationConstant.ARTICLE_NOT_NULL)
    @NotEmpty(message = ValidationConstant.ARTICLE_CONTENT_NOT_EMPTY)
    @Size(min = 4, message = ValidationConstant.ARTICLE_CONTENT_SIZE_VALIDATION)
    private String content;
    
    @URL(message = ValidationConstant.URL_INVALID_FORMAT)
    private String imageURL;
}
