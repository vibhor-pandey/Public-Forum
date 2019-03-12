package com.forum.publicforum.model.view;

import com.forum.publicforum.model.Comment;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentView extends BaseView{
    private long commentId;
    private long userId;
    private String comment;
    private long createTS;
    
    
    public CommentView(Comment comment) {
        super();
        this.commentId = comment.getId();
        this.userId = comment.getUserID();
        this.comment = comment.getComment();
        this.createTS = comment.getCreateTS();
    }
    
    
}
