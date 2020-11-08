package ru.itis.javalab.models;


import lombok.*;

import java.util.UUID;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder


public class User {
    private Long id;
    private String firstName;
    private String lastName;
    private Integer age;
    private UUID uuid;
    private String email;
    private String hashPassword;
}
