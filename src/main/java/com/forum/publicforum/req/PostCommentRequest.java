package com.forum.publicforum.req;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

import com.forum.publicforum.constant.ValidationConstant;

@Getter
@Setter
public class PostCommentRequest extends AuthRequest {
    
    @Email
    @NotEmpty(message = ValidationConstant.EMAIL_NOT_EMPTY)
    @NotNull(message = ValidationConstant.EMAIL_NOT_NULL)
    @Size(min = 4, max = 30, message = ValidationConstant.EMAIL_SIZE_VALIDATION)
    private String email;
    
    @Positive(message = ValidationConstant.POST_ID_INVALID)
    @Digits(fraction = 0, integer = 6)
    private int articleId;
    
    @NotEmpty(message = ValidationConstant.COMMENT_NOT_EMPTY)
    @NotNull(message = ValidationConstant.COMMENT_NOT_NULL)
    @Size(min = 2, message = ValidationConstant.COMMENT_SIZE_VALIDATION)
    private String comment;
}
