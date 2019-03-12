package com.forum.publicforum.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.forum.publicforum.constant.ValidationConstant;


@Entity
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonInclude(value = Include.NON_NULL)
public class User extends Base{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false)
    @NotNull
    private int id;
    
    @Column(length = 50)
    @NotNull(message = ValidationConstant.NAME_NOT_NULL)
    @NotEmpty(message = ValidationConstant.NAME_NOT_EMPTY)
    @Size(min = 3, message = ValidationConstant.NAME_SIZE_VALIDATION)
    private String name;
    
    @Column(nullable = false, length = 200)
    @NotNull(message = ValidationConstant.PASSWORD_NOT_NULL)
    @NotEmpty(message = ValidationConstant.PASSWORD_NOT_EMPTY)
    private String password;
    
    @Column(nullable = false, length = 30, unique=true)
    @NotNull(message = ValidationConstant.EMAIL_NOT_EMPTY)
    @NotEmpty(message = ValidationConstant.EMAIL_NOT_EMPTY)
    @Size(min = 4, message = ValidationConstant.EMAIL_SIZE_VALIDATION)
    @Email
    private String email;
    
    @Column(nullable = false, updatable = false)
    private Long createTS = System.currentTimeMillis();;
    
    @Column(nullable = false)
    private Long lastLogin = System.currentTimeMillis();
    
}
