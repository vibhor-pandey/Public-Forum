package com.forum.publicforum.model.view;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserView {
    private Integer userId;
    private String name;
    
    public UserView(Integer userId, String name) {
        super();
        this.userId = userId;
        this.name = name;
    }
}
