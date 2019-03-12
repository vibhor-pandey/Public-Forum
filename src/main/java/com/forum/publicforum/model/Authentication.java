package com.forum.publicforum.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user_auth")
@Getter
@Setter
public class Authentication extends Base{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false)
    @NotNull
    private int id;
    
    @Column(nullable = false)
    @NotNull
    @NotEmpty
    @Size(min = 4, max = 32)
    private String auth;
    
    @Column(nullable = false, updatable = false)
    @NotNull
    private int user_id;
}
