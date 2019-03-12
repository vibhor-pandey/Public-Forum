package com.forum.publicforum.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.forum.publicforum.model.Comment;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long>{

}
