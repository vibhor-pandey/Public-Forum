package com.assignment.forum.publicforum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static com.forum.publicforum.constant.Path.HOST;
import static com.forum.publicforum.constant.Path.FORUM;
import static com.forum.publicforum.constant.Path.GET_ARTICLES;
import static com.forum.publicforum.constant.Path.PAGING;

import com.forum.publicforum.controller.PostController;
import com.forum.publicforum.controller.UserController;
import com.forum.publicforum.model.Comment;
import com.forum.publicforum.model.view.ArticleView;
import com.forum.publicforum.model.view.UserView;
import com.forum.publicforum.req.GetArticlesRequest;
import com.forum.publicforum.res.BaseResponse;
import com.forum.publicforum.res.UserArticlesResponse;

@RunWith(SpringRunner.class)
@WebMvcTest(value = PostController.class)
public class PublicForumTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private UserController userController;
    
    @MockBean
    private PostController postController;
    
    @Test
    public void getOwnArticles() throws Exception {
        
        //Response Body
        ArticleView articleView = new ArticleView();
        articleView.setArticleId(149);
        articleView.setContent("My name is Test. This is my First post");
        articleView.setCreateTS(System.currentTimeMillis());
        articleView.setImageUrl(null);
        articleView.setUser(new UserView(147, "Test Forum"));
        List<Comment> comments = new ArrayList<Comment>();
        articleView.setComments(comments);
        
        List<ArticleView> articleViews = Arrays.asList(articleView);
        UserArticlesResponse articlesResponse = new UserArticlesResponse();
        articlesResponse.setArticles(articleViews);
        articlesResponse.setSuccess(true);
       
        ResponseEntity<BaseResponse> responseEntity = new ResponseEntity<>(articlesResponse, HttpStatus.FOUND);
        
        // Request Body
        GetArticlesRequest request = new GetArticlesRequest();
        request.setEmail("test@gmail.com");
        request.setOwnArticles(true);
        
        given(postController.getUserArticles(request, null)).willReturn(responseEntity);
        
        mockMvc.perform(get(HOST + FORUM + GET_ARTICLES + PAGING)
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].articles", hasSize(1)));

    }
    
    
    @Test
    public void getOthersArticles() throws Exception {
        
        //Response Body
        ArticleView articleView = new ArticleView();
        articleView.setArticleId(149);
        articleView.setContent("My name is Test. This is my First post");
        articleView.setCreateTS(System.currentTimeMillis());
        articleView.setImageUrl(null);
        articleView.setUser(new UserView(147, "Test Forum"));
        List<Comment> comments = new ArrayList<Comment>();
        articleView.setComments(comments);
        
        List<ArticleView> articleViews = Arrays.asList(articleView);
        UserArticlesResponse articlesResponse = new UserArticlesResponse();
        articlesResponse.setArticles(articleViews);
        articlesResponse.setSuccess(false);
       
        ResponseEntity<BaseResponse> responseEntity = new ResponseEntity<>(articlesResponse, HttpStatus.FOUND);
        
        // Request Body
        GetArticlesRequest request = new GetArticlesRequest();
        request.setEmail("test@gmail.com");
        request.setOwnArticles(true);
        
        given(postController.getUserArticles(request, null)).willReturn(responseEntity);
        
        mockMvc.perform(get(HOST + FORUM + GET_ARTICLES + PAGING)
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk());

    }
}
