package com.example.domain;

import javax.persistence.Entity;

import com.example.domain.Role.Type;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class Manager extends User {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @JsonCreator
    public Manager( @JsonProperty("username") String username, @JsonProperty("password") String password, @JsonProperty("firstname") String firstname, 
                    @JsonProperty("lastname") String lastname, @JsonProperty("email") String email) {
        super(username, password, firstname, lastname, email);
        getRoles().clear();
        getRoles().add(new Role(Type.MANAGER));
    }


}