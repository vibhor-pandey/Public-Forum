package com.forum.publicforum.constant;

public class ValidationConstant {

    public static final String NAME_NOT_EMPTY                  = "Name must not be EMPTY";
    public static final String NAME_NOT_NULL                   = "Name must not be NULL";
    public static final String NAME_SIZE_VALIDATION            = "Name should have atleast 3 characters";

    public static final String PASSWORD_NOT_NULL               = "Password must not be NULL";
    public static final String PASSWORD_NOT_EMPTY              = "Password must not be EMPTY";
    public static final String PASSWORD_SIZE_VALIDATION        = "Password must greater than or equal to 5 and less than or equal to 16.";

    public static final String EMAIL_NOT_NULL                  = "Email must not be NULL";
    public static final String EMAIL_NOT_EMPTY                 = "Email must not be EMPTY";
    public static final String EMAIL_SIZE_VALIDATION           = "Email must greater than or equal to 4 and less than or equal to 30.";

    public static final String ARTICLE_NOT_NULL                = "Article must not be NULL";
    public static final String ARTICLE_CONTENT_NOT_EMPTY       = "Article Content must not be EMPTY";
    public static final String ARTICLE_CONTENT_SIZE_VALIDATION = "Article Content must greater than or equal to 4";

    public static final String AUTH_TOKEN_NOT_EMPTY            = "Auth Toke must not be EMPTY";
    public static final String AUTH_TOKEN_NOT_NULL             = "Auth Toke must not be NULL";

    public static final String URL_INVALID_FORMAT              = "Inavlid format of the URL";

    public static final String POST_ID_NOT_EMPTY               = "Post ID must not be EMPTY";
    public static final String POST_ID_NOT_NULL                = "Post ID must not be NULL";
    public static final String POST_ID_INVALID                 = "Invalid Post ID";

    public static final String COMMENT_NOT_EMPTY               = "Post ID must not be EMPTY";
    public static final String COMMENT_NOT_NULL                = "Post ID must not be NULL";
    public static final String COMMENT_SIZE_VALIDATION         = "Comment should have atleast 2 characters";

    public static final String PAGE_NUMBER_INVALID             = "Invalid Page Number";
    public static final String SIZE_INVALID                    = "Invalid Size";
}
