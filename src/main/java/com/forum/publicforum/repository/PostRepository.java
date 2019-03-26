package com.forum.publicforum.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.forum.publicforum.model.Article;

@Repository
public interface PostRepository extends CrudRepository<Article, Integer> {
    
//    @Query(value = "SELECT * FROM article WHERE user_id != :user_id ORDER BY createts DESC ", nativeQuery = true)
//    Stream<Article> findOthersArticlesById(@Param("user_id") int user_id);
    
    @Query(value = "SELECT * FROM article WHERE user_id != :user_id ORDER BY createts DESC ", nativeQuery = true)
    Page<Article> findOthersArticlesById(@Param("user_id") int user_id, Pageable pageable);
    
//    @Query(value = "SELECT * FROM article WHERE user_id = :user_id ORDER BY createts DESC ", nativeQuery = true)
//    Stream<Article> findOwnArticlesById(@Param("user_id") int user_id);
    
    @Query(value = "SELECT * FROM article WHERE user_id = :user_id ORDER BY createts DESC ", nativeQuery = true)
    Page<Article> findOwnArticlesById(@Param("user_id") int user_id, Pageable page);
}
