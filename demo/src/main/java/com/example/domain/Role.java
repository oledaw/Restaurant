package com.example.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
public class Role implements Serializable{

    private static final long serialVersionUID = 1L;

public enum Type {
    ADMIN{
        @Override
        public String toString() {
            return "ADMIN";
        }
    },
    MANAGER{
        @Override
        public String toString() {
            return "MANAGER";
        }
    },
    WORKER{
        @Override
        public String toString() {
            return "WORKER";
        }
    },
    USER{
        @Override
        public String toString() {
            return "USER";
        }
    };
}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    long id;

    @Getter
    @Setter
    private String name;

    public Role(Type type) {
        name = type.toString();
    };
}




