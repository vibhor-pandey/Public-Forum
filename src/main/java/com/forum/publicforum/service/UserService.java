package com.forum.publicforum.service;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.forum.publicforum.constant.MessageConstant;
import com.forum.publicforum.model.User;
import com.forum.publicforum.repository.UserRepository;
import com.forum.publicforum.req.LoginRequest;
import com.forum.publicforum.req.SignupRequest;
import com.forum.publicforum.res.BaseResponse;
import com.forum.publicforum.res.LoginResponse;
import com.forum.publicforum.res.SignupResponse;
import com.forum.publicforum.util.EncryptionUtil;
import com.forum.publicforum.util.ErrorCode;

@Service
public class UserService extends BaseService{
    
    @Autowired
    UserRepository userRepository;
    
    @Autowired
    AuthenticationService authenticationService;
    
    /**
     * 
     * @param user
     * @return
     */
    public Optional<User> saveUser(SignupRequest request) throws Exception {
        User user = new User();
        user.setEmail(request.getEmail());
        user.setName(request.getName());
        user.setPassword(EncryptionUtil.getEncryptionUtil().encrypt(request.getPassword()));
        
        return Optional.ofNullable(userRepository.save(user));
    }
    
    /**
     * 
     * @param email
     * @return
     */
    public boolean isUserExist(String email) {
        return getUserByEmail(email).isPresent();
    }
    
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    
    
    public Optional<User> getUserById(int id) {
        return userRepository.findById(id);
    }
    
    
    /**
     * 
     * @param email
     * @return
     */
    public Optional<Integer> getUserIdByEmail(String email) {
        return Optional.ofNullable(userRepository.findByEmail(email).get().getId());
    }
    
    
    /**
     * 
     * @param user
     * @return
     * @throws Exception
     */
    public BaseResponse create(SignupRequest request) throws Exception {
        
        SignupResponse response = new SignupResponse();
        
        getUserByEmail(request.getEmail()).ifPresent(usr -> {
            getErrorResponse(ErrorCode.USER_ALREADY_EXIST, response);
        });
        
        if(Objects.nonNull(response.getErrorCode())) {
            return response;
        }
        
        saveUser(request).ifPresent(usr -> {
            response.setEmail(usr.getEmail());
            response.setSuccess(true);
            response.setMessage(MessageConstant.REGISTRATION_SUCCESSFUL);
        });
        
        if(response.isSuccess()) {
            return response;
        }
        return getErrorResponse(ErrorCode.UNKOWN_ERROR, response);
    }
    
    /**
     * 
     * @param loginRequest
     * @return
     * @throws Exception
     */
    public BaseResponse login(LoginRequest loginRequest) throws Exception{
        return execute(loginRequest.getEmail(), loginRequest.getPassword());
    }
    
    
    /**
     * 
     * @param email
     * @param password
     * @return
     * @throws Exception
     */
    public BaseResponse execute(String email, String password) throws Exception {
        LoginResponse loginResponse = new LoginResponse();
        
        getUserByEmail(email).ifPresent(usr -> {
            
            try {
                if(password.equals(EncryptionUtil.getEncryptionUtil().decrypt(usr.getPassword()))) {
                    //Create Authentication Token
                    authenticationService.createOrUpdateUserAuth(usr.getId(),
                            getDataToBeHashed(usr.getEmail())).ifPresent(
                                    auth -> loginResponse.setAuth_token(auth.getAuth()));
                    
                    loginResponse.setSuccess(true);
                    loginResponse.setMessage(MessageConstant.LOGIN_SUCCESSFUL);
                } else {
                    getErrorResponse(ErrorCode.WRONG_CREDENTIALS, loginResponse);
                }
            } catch(Exception e) {
                getErrorResponse(ErrorCode.UNKOWN_ERROR, loginResponse);
            }
        });
        
        if(loginResponse.isSuccess()) {
           return loginResponse; 
        } else if(Objects.nonNull(loginResponse.getErrorCode())) {
            return loginResponse;
        }
        return getErrorResponse(ErrorCode.USER_NOT_EXIST, loginResponse);
    }
    
    
    /**
     * 
     * @param userId
     * @param email
     * @return
     */
    private String getDataToBeHashed(String email) {
        StringBuilder builder = new StringBuilder("");
        builder.append(System.currentTimeMillis()).append(email);
        
        return builder.toString();
    }
}
