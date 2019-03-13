package com.forum.publicforum.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.forum.publicforum.constant.MessageConstant;
import com.forum.publicforum.model.Article;
import com.forum.publicforum.model.Comment;
import com.forum.publicforum.model.User;
import com.forum.publicforum.model.view.ArticleView;
import com.forum.publicforum.model.view.UserView;
import com.forum.publicforum.repository.CommentRepository;
import com.forum.publicforum.repository.PostRepository;
import com.forum.publicforum.req.GetArticlesRequest;
import com.forum.publicforum.req.PostArticleRequest;
import com.forum.publicforum.req.PostCommentRequest;
import com.forum.publicforum.res.BaseResponse;
import com.forum.publicforum.res.PostArticleResponse;
import com.forum.publicforum.res.PostCommentResponse;
import com.forum.publicforum.res.UserArticlesResponse;
import com.forum.publicforum.util.ErrorCode;

@Service
public class PostService extends BaseService {

    @Autowired
    PostRepository              postRepository;

    @Autowired
    CommentRepository           commentRepository;

    @Autowired
    UserService                 userService;
    
    @Autowired
    AuthenticationService authenticationService;

    /**
     * 
     * @param id
     * @return
     */
    public Optional<Article> getArticleByID(int id) {
        return postRepository.findById(id);
    }

    /**
     * 
     * @param user
     * @param request
     * @return
     */
    private Optional<Article> saveUserArticleByUserId(Integer userId, PostArticleRequest request) {
        Article article = new Article();
        article.setContent(request.getContent());
        article.setUser(userService.getUserById(userId).get());

        if (!Objects.nonNull(request.getImageURL())) {
            article.setImageURL(request.getImageURL());
        }

        return Optional.ofNullable(postRepository.save(article));
    }

    
    /**
     * 
     * @param article
     * @param userId
     * @param request
     * @return
     */
    private Optional<Comment> saveArticleCommentByUserId(Article article, int userId, PostCommentRequest request) {
        Comment comment = new Comment();
        comment.setUserID(userId);
        comment.setArticle(article);
        comment.setComment(request.getComment());
        return Optional.ofNullable(commentRepository.save(comment));
    }

    /**
     * 
     * @param articleRequest
     * @return
     */
    public BaseResponse postArticleByEmail(PostArticleRequest articleRequest) {
        PostArticleResponse response = new PostArticleResponse();

        // Check if User Exists
        userService.getUserByEmail(articleRequest.getEmail()).ifPresent(user -> {
            // Check Whether User is Authenticated
            if(!authenticationService.isUserAuthExists(user.getId(), articleRequest.getAuth_token())) {
                // If User in Not Authenticated then send Error Message
                getErrorResponse(ErrorCode.USER_NOT_AUTHENTICATED, response);
            } else {
                // Else Save Post and send Success Message
                saveUserArticleByUserId(user.getId(), articleRequest).ifPresent((article) -> {
                    response.setMessage(MessageConstant.ARTICLE_POSTED_SUCCESSFULLY);
                    response.setSuccess(true);
                });
            }
            });

        if (response.isSuccess() || Objects.nonNull(response.getErrorCode())) {
            return response;
        }
        // If User Not Existed for Email Return Error Message
        return getErrorResponse(ErrorCode.USER_NOT_EXIST, response);
    }

    /**
     * 
     * @param commentRequest
     * @return
     */
    public BaseResponse postCommentByEmailAndPostID(PostCommentRequest commentRequest) {
        PostCommentResponse response = new PostCommentResponse();
        // Check if Commenting User Exists
        userService.getUserByEmail(commentRequest.getEmail()).ifPresent(usr -> {
            // Check Whether User is Authenticated
            if(!authenticationService.isUserAuthExists(usr.getId(), commentRequest.getAuth_token())) {
                // If User in Not Authenticated then send Error Message
                getErrorResponse(ErrorCode.USER_NOT_AUTHENTICATED, response);
            } else {
                // Check if Post Exists
                getArticleByID(commentRequest.getArticleId()).ifPresent(article -> {
                    // Save Article
                        saveArticleCommentByUserId(article, usr.getId(), commentRequest).ifPresent(comment -> {
                            response.setMessage(MessageConstant.COMMENT_POSTED_SUCCESSFULLY);
                            response.setSuccess(true);
                        });
                    });

                // Article Doesn't Exist
                if (!response.isSuccess()) {
                    getErrorResponse(ErrorCode.ARTICLE_NOT_EXIST, response);
                }
            }
        });

        if (response.isSuccess() || Objects.nonNull(response.getErrorCode())) {
            return response;
        }
        
        // User doesn't exist
        return getErrorResponse(ErrorCode.USER_NOT_EXIST, response);
    }

    /**
     * 
     * @param email
     * @param page
     * @param size
     * @return
     */
    @Transactional(readOnly = true)
    public BaseResponse getArticlesByEmail(GetArticlesRequest request, Pageable pageable) {

        Optional<User> optionalUser = userService.getUserByEmail(request.getEmail());
        
        UserArticlesResponse response = new UserArticlesResponse();

        optionalUser.ifPresent(user -> {
            Page<Article> articleStream = null;
            
            if(!request.isOwnArticles()) {
                articleStream = postRepository.findOthersArticlesById(user.getId(), pageable);
            } else {
                articleStream = postRepository.findOwnArticlesById(user.getId(), pageable);
            }

            List<ArticleView> articles = articleStream.getContent().stream()
                    .map(artcl -> 
                        new ArticleView(artcl.getId(), new UserView(artcl.getUser().getId(),
                        artcl.getUser().getName()), artcl.getContent(),
                        artcl.getComments(), artcl.getCreateTS(), artcl.getImageURL()))
                    .collect(Collectors.toList());
                    

            response.setSuccess(true);
            response.setArticles(articles);
        });
        
        if(response.isSuccess()) {
            return response;
        }
        return getErrorResponse(ErrorCode.USER_NOT_EXIST, response);
    }
}
