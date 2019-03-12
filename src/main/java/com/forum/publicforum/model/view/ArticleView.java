package com.forum.publicforum.model.view;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.forum.publicforum.model.Comment;
import com.forum.publicforum.model.User;

@Getter
@Setter
@JsonInclude(value = Include.NON_NULL)
public class ArticleView extends BaseView{
    
    private Integer articleId;
    private UserView user;
    private String content;
    private Long createTS;
    private String imageUrl;
    private List<Comment> comments;
    
    
    public ArticleView(Integer articleId, UserView user, String content, List<Comment> comments, Long createTS, String imageUrl) {
        super();
        this.articleId = articleId;
        this.user = user;
        this.content = content;
        this.createTS = createTS;
        this.comments = comments;
        this.imageUrl = imageUrl;
    }
    
    

}
