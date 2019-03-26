package com.forum.publicforum.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.URL;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.forum.publicforum.constant.ValidationConstant;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "article")
@Getter
@Setter
@JsonInclude(value = Include.NON_NULL)
public class Article extends Base{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false)
    @NotNull
    private int id;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;
    
    @Column(nullable = false)
    @NotNull(message = ValidationConstant.ARTICLE_CONTENT_NOT_EMPTY)
    @NotEmpty(message = ValidationConstant.ARTICLE_CONTENT_NOT_EMPTY)
    @Size(min = 4, message = ValidationConstant.ARTICLE_CONTENT_SIZE_VALIDATION)
    private String content;
    
    @URL(message = ValidationConstant.URL_INVALID_FORMAT)
    private String imageURL;
    
    @Column(nullable = false, updatable = false)
    private long createTS = System.currentTimeMillis();
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "article")
    private List<Comment> comments = new ArrayList<Comment>();

}
