package com.assignment.forum.publicforum.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hamcrest.core.Is;
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
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static com.forum.publicforum.constant.Path.HOST;
import static com.forum.publicforum.constant.Path.FORUM;
import static com.forum.publicforum.constant.Path.GET_ARTICLES;
import static com.forum.publicforum.constant.Path.PAGING;

import com.assignment.forum.publicforum.constant.TestConstant;
import com.forum.publicforum.controller.PostController;
import com.forum.publicforum.model.Comment;
import com.forum.publicforum.model.view.ArticleView;
import com.forum.publicforum.model.view.UserView;
import com.forum.publicforum.req.GetArticlesRequest;
import com.forum.publicforum.res.BaseResponse;
import com.forum.publicforum.res.UserArticlesResponse;

@RunWith(SpringRunner.class)
@WebMvcTest(value = PostController.class)
public class PostControllerTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private PostController postController;
    
    /**
     * 
     * @throws Exception
     */
    @Test
    public void getOwnArticles() throws Exception {
        
        //Response Body
        ArticleView articleView = new ArticleView();
        articleView.setContent(TestConstant.TEST_USER_ARTICLE_CONTENT);
        articleView.setImageUrl(null);
        articleView.setUser(new UserView(TestConstant.DEFAULT_USER_ID, TestConstant.TEST_USER_NAME));
        List<Comment> comments = new ArrayList<Comment>();
        articleView.setComments(comments);
        List<ArticleView> articleViews = Arrays.asList(articleView);
        
        UserArticlesResponse articlesResponse = new UserArticlesResponse();
        articlesResponse.setArticles(articleViews);
       
        ResponseEntity<BaseResponse> responseEntity = new ResponseEntity<>(articlesResponse, HttpStatus.FOUND);
        
        // Request Body
        GetArticlesRequest request = new GetArticlesRequest();
        request.setEmail(TestConstant.TEST_USER_EMAIL);
        request.setOwnArticles(true);
        
        given(postController.getUserArticles(request, null)).willReturn(responseEntity);
        
        mockMvc.perform(get(HOST + FORUM + GET_ARTICLES + PAGING)
                .contentType(APPLICATION_JSON))
                .andExpect(status().isFound())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].content", Is.is(articleView.getContent())))
                .andExpect(jsonPath("$[0].user.name", Is.is(articleView.getUser().getName())));

    }
    
    
    /**
     * 
     * @throws Exception
     */
    @Test
    public void getOthersArticles() throws Exception {
        
        ResponseEntity<BaseResponse> responseEntity = new ResponseEntity<>(new UserArticlesResponse(), HttpStatus.FOUND);
        
        // Request Body
        GetArticlesRequest request = new GetArticlesRequest();
        request.setEmail(TestConstant.TEST_USER_EMAIL);
        request.setOwnArticles(false);
        
        given(postController.getUserArticles(request, null)).willReturn(responseEntity);
        
        mockMvc.perform(get(HOST + FORUM + GET_ARTICLES + PAGING)
                .contentType(APPLICATION_JSON))
                .andExpect(status().isFound())
                .andExpect(jsonPath("$.success", Is.is(Boolean.TRUE)));

    }
}
