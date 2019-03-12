package com.forum.publicforum.res;

import java.util.List;

import com.forum.publicforum.model.view.ArticleView;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserArticlesResponse extends BaseResponse{
    private List<ArticleView> articles;
}
