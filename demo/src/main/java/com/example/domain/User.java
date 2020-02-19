package com.example.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.example.domain.Role.Type;
import com.example.validator.Username;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @GeneratedValue(strategy = GenerationType.TABLE)
    @Id
    private long id;
    @Getter
    @Setter
    @Username
    private String username;
    @Getter
    @Setter
    private String password;
    @Getter
    @Setter
    private String firstname;
    @Getter
    @Setter
    @NotEmpty(message = "Nie może być puste")
    private String lastname;
    @Getter
    @Setter
    @Email(message = "Zły format email")
    private String email;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
        @JoinTable(
                name = "users_roles",
                joinColumns = @JoinColumn(
                        name = "user_id", referencedColumnName = "id"),
                inverseJoinColumns = @JoinColumn(
                        name = "role_name", referencedColumnName = "name"))
    @Getter
    @Setter
    private Set<Role> roles = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Getter
    @Setter
    Set<CustomerOrder> orders;

    @JsonCreator
    public User( @JsonProperty("username") String username, @JsonProperty("password") String password, @JsonProperty("firstname") String firstname, 
                    @JsonProperty("lastname") String lastname, @JsonProperty("email") String email) {
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        getRoles().add(new Role(Type.USER));
    }



}
