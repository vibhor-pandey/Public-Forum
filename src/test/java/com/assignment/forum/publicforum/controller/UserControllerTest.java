package com.assignment.forum.publicforum.controller;

import static com.forum.publicforum.constant.Path.FORUM;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static com.forum.publicforum.constant.Path.SIGNUP;
import static com.forum.publicforum.constant.Path.LOGIN;

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

import com.assignment.forum.publicforum.constant.TestConstant;
import com.assignment.forum.publicforum.util.TestUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.forum.publicforum.PublicForumApplication;
import com.forum.publicforum.controller.UserController;
import com.forum.publicforum.req.SignupRequest;
import com.forum.publicforum.util.ErrorCode;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@ContextConfiguration(classes = PublicForumApplication.class)
@AutoConfigureMockMvc
@WebAppConfiguration
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    
    /**
     * 
     * @throws JsonProcessingException
     * @throws Exception
     */
    @Test
    public void signUpUserAlreadyExistTest() throws JsonProcessingException, Exception {
        SignupRequest request = new SignupRequest();
        request.setEmail(TestConstant.SIGNUP_TEST_EXISTING_USER_EMAIL);
        request.setName(TestConstant.SIGNUP_TEST_USER_NAME);
        request.setPassword(TestConstant.SIGNUP_TEST_USER_PASSWORD);
        
        mockMvc.perform(post(FORUM + SIGNUP)
                .header("Content-Type", MediaType.APPLICATION_JSON)
                .content(TestUtil.convertRequestObjectToString(request))
                .characterEncoding("utf-8"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.success", Is.is(false)))
                .andExpect(jsonPath("$.error", Is.is(ErrorCode.USER_ALREADY_EXIST.getCode())));
    }
    
    
    /**
     * 
     * @throws JsonProcessingException
     * @throws Exception
     */
    @Test
    public void signUpSuccessTest() throws JsonProcessingException, Exception {
        SignupRequest request = new SignupRequest();
        request.setEmail(TestConstant.SIGNUP_TEST_USER_EMAIL);
        request.setName(TestConstant.SIGNUP_TEST_USER_NAME);
        request.setPassword(TestConstant.SIGNUP_TEST_USER_PASSWORD);
        
        mockMvc.perform(post(FORUM + SIGNUP)
                .header("Content-Type", MediaType.APPLICATION_JSON)
                .content(TestUtil.convertRequestObjectToString(request))
                .characterEncoding("utf-8"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.success", Is.is(true)))
                .andExpect(jsonPath("$.email", Is.is(request.getEmail())));
    }
    
    
    /**
     * 
     * @throws JsonProcessingException
     * @throws Exception
     */
    @Test
    public void loginUserNotExistsTest() throws JsonProcessingException, Exception {
        SignupRequest request = new SignupRequest();
        request.setEmail(TestConstant.WRONG_TEST_USER_EMAIL);
        request.setPassword(TestConstant.LOGIN_TEST_USER_PASSWORD);
        
        mockMvc.perform(post(FORUM + LOGIN)
                .header("Content-Type", MediaType.APPLICATION_JSON)
                .content(TestUtil.convertRequestObjectToString(request))
                .characterEncoding("utf-8"))
                .andExpect(status().isFound())
                .andExpect(jsonPath("$.success", Is.is(false)))
                .andExpect(jsonPath("$.error", Is.is(ErrorCode.USER_NOT_EXIST.getCode())));
    }
    
    
    /**
     * 
     * @throws JsonProcessingException
     * @throws Exception
     */
    @Test
    public void loginWrongCredentialsTest() throws JsonProcessingException, Exception {
        SignupRequest request = new SignupRequest();
        request.setEmail(TestConstant.LOGIN_TEST_USER_EMAIL);
        request.setPassword(TestConstant.LOGIN_TEST_USER_WRONG_PASSWORD);
        
        mockMvc.perform(post(FORUM + LOGIN)
                .header("Content-Type", MediaType.APPLICATION_JSON)
                .content(TestUtil.convertRequestObjectToString(request))
                .characterEncoding("utf-8"))
                .andExpect(status().isFound())
                .andExpect(jsonPath("$.success", Is.is(false)))
                .andExpect(jsonPath("$.error", Is.is(ErrorCode.WRONG_CREDENTIALS.getCode())));
    }
    
    
    /**
     * 
     * @throws JsonProcessingException
     * @throws Exception
     */
    @Test
    public void loginSuccessTest() throws JsonProcessingException, Exception {
        SignupRequest request = new SignupRequest();
        request.setEmail(TestConstant.LOGIN_TEST_USER_EMAIL);
        request.setPassword(TestConstant.LOGIN_TEST_USER_PASSWORD);
        
        mockMvc.perform(post(FORUM + LOGIN)
                .header("Content-Type", MediaType.APPLICATION_JSON)
                .content(TestUtil.convertRequestObjectToString(request))
                .characterEncoding("utf-8"))
                .andExpect(status().isFound())
                .andExpect(jsonPath("$.success", Is.is(true)));
    }
    
}
