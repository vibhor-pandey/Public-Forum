package com.assignment.forum.publicforum.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hamcrest.core.Is;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static com.forum.publicforum.constant.Path.FORUM;
import static com.forum.publicforum.constant.Path.GET_ARTICLES;
import static com.forum.publicforum.constant.Path.POST_ARTICLE;

import com.assignment.forum.publicforum.constant.TestConstant;
import com.assignment.forum.publicforum.util.TestUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.forum.publicforum.PublicForumApplication;
import com.forum.publicforum.controller.PostController;
import com.forum.publicforum.model.Comment;
import com.forum.publicforum.model.view.ArticleView;
import com.forum.publicforum.model.view.UserView;
import com.forum.publicforum.req.GetArticlesRequest;
import com.forum.publicforum.req.PostArticleRequest;
import com.forum.publicforum.res.UserArticlesResponse;
import com.forum.publicforum.util.ErrorCode;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@ContextConfiguration(classes = PublicForumApplication.class)
@AutoConfigureMockMvc
@WebAppConfiguration
public class PostControllerTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @InjectMocks
    private PostController postController;
    
    @Test
    public void postArticleSuccessTest() throws JsonProcessingException, Exception {
        
        //Request Body
        PostArticleRequest request = new PostArticleRequest();
        request.setAuth_token(TestConstant.TEST_USER_AUTH_TOKEN);
        request.setEmail(TestConstant.TEST_USER_EMAIL);
        request.setContent(TestConstant.TEST_USER_ARTICLE_CONTENT);
        
        mockMvc.perform(post(FORUM + POST_ARTICLE)
                .header("Content-Type", MediaType.APPLICATION_JSON)
                .content(TestUtil.convertRequestObjectToString(request))
                .characterEncoding("utf-8"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.success", Is.is(true)));
    }
    
    @Test
    public void getArticlesUserNotExistsTest() throws JsonProcessingException, Exception {
        GetArticlesRequest request = new GetArticlesRequest();
        request.setEmail(TestConstant.WRONG_TEST_USER_EMAIL);
        request.setOwnArticles(true);
        
        mockMvc.perform(post(FORUM + GET_ARTICLES)
                .header("Content-Type", MediaType.APPLICATION_JSON)
                .content(TestUtil.convertRequestObjectToString(request))
                .characterEncoding("utf-8"))
                .andExpect(status().isFound())
                .andExpect(jsonPath("$.success", Is.is(false)))
                .andExpect(jsonPath("$.error", Is.is(ErrorCode.USER_NOT_EXIST.getCode())));
    }
    
    
    
    /**
     * @throws Exception
     */
    @Test
    public void getOwnArticlesSuccessTest() throws Exception {
        
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
        
        // Request Body
        GetArticlesRequest request = new GetArticlesRequest();
        request.setEmail(TestConstant.TEST_USER_EMAIL);
        request.setOwnArticles(true);

        mockMvc.perform(post(FORUM + GET_ARTICLES)
                .header("Content-Type", MediaType.APPLICATION_JSON)
                .content(TestUtil.convertRequestObjectToString(request))
                .characterEncoding("utf-8"))
                .andExpect(status().isFound())
                .andExpect(jsonPath("$.success", Is.is(true)));
    }
    
    
    /**
     * 
     * @throws Exception
     */
    @Test
    public void getOthersArticlesSuccessTest() throws Exception {
        
        // Request Body
        GetArticlesRequest request = new GetArticlesRequest();
        request.setEmail(TestConstant.TEST_USER_EMAIL);
        request.setOwnArticles(false);

        mockMvc.perform(post(FORUM + GET_ARTICLES)
                .header("Content-Type", MediaType.APPLICATION_JSON)
                .content(TestUtil.convertRequestObjectToString(request))
                .characterEncoding("utf-8"))
                .andExpect(status().isFound())
                .andExpect(jsonPath("$.success", Is.is(Boolean.TRUE)));
    }
}
