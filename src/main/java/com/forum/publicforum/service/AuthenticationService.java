package com.forum.publicforum.service;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.forum.publicforum.model.Authentication;
import com.forum.publicforum.repository.AuthenticationRepository;
import com.forum.publicforum.util.AuthUtil;

@Service
public class AuthenticationService extends BaseService {

    @Autowired
    AuthenticationRepository authenticationRepository;
    
    /**
     * 
     * @param userId
     * @return
     */
    public Optional<Authentication> getUserAuthByUserId(int userId) {
        return authenticationRepository.findUserAuthByUserId(userId);
    }
    
    
    /**
     * 
     * @param userId
     * @param auth
     * @return
     */
    public boolean isUserAuthExists(int userId, String auth) {
        return authenticationRepository.findUserAuthByUserIdAndAuth(userId, auth).isPresent();
    }
    
    
    /**
     * 
     * @param userId
     * @param dataToBeHashed
     * @return
     * @throws Exception
     */
    public Optional<Authentication> createOrUpdateUserAuth(int userId, String dataToBeHashed) throws Exception {
        String hashedData = AuthUtil.getInstance().hashData(dataToBeHashed);
        Optional<Authentication> optionalAuth = getUserAuthByUserId(userId).map(auth -> {
            auth.setAuth(hashedData);
            return auth;
        });
        
        if(!optionalAuth.isPresent()) {
            Authentication newAuth = new Authentication();
            newAuth.setAuth(hashedData);
            newAuth.setUser_id(userId);
            optionalAuth = Optional.ofNullable(newAuth);
        }
        
        return Optional.ofNullable(authenticationRepository.save(optionalAuth.get()));
    }
}
