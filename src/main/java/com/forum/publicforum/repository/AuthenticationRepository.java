package com.forum.publicforum.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.forum.publicforum.model.Authentication;

@Repository
public interface AuthenticationRepository extends CrudRepository<Authentication, Long>{

    @Query(value = "SELECT * FROM user_auth WHERE user_id = :user_id AND auth = :auth", nativeQuery = true)
    public Optional<Authentication> findUserAuthByUserIdAndAuth(@Param("user_id") int user_id,
            @Param("auth") String auth);
    
    @Query(value = "SELECT * FROM user_auth WHERE user_id = :user_id ", nativeQuery = true)
    public Optional<Authentication> findUserAuthByUserId(@Param("user_id") int user_id);
}
