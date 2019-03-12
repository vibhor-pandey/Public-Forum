package com.forum.publicforum.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.forum.publicforum.req.GetArticlesRequest;
import com.forum.publicforum.req.PostArticleRequest;
import com.forum.publicforum.req.PostCommentRequest;
import com.forum.publicforum.res.BaseResponse;
import com.forum.publicforum.service.PostService;

@Controller
@RequestMapping(value = "/forum")
public class PostController {
    
    @Autowired
    PostService postService;
    
    @RequestMapping(value = "/post/article", method = RequestMethod.POST)
    public ResponseEntity<BaseResponse> postArticle(@Valid @RequestBody PostArticleRequest request) {
        return new ResponseEntity<BaseResponse>(postService.postArticleByEmail(request), HttpStatus.CREATED);
    }
    
    
    @RequestMapping(value = "/post/comment", method = RequestMethod.POST)
    public ResponseEntity<BaseResponse> postComment(@Valid @RequestBody PostCommentRequest request) {
        return new ResponseEntity<BaseResponse>(postService.postCommentByEmailAndPostID(request), HttpStatus.CREATED);
    }
    
    @RequestMapping(value = "/articles", method = RequestMethod.POST)
    public ResponseEntity<BaseResponse> getUserArticles(@Valid @RequestBody GetArticlesRequest request, Pageable pageable) {
        return new ResponseEntity<BaseResponse>(postService.getArticlesByEmail(request, pageable), HttpStatus.CREATED);
    }
}
