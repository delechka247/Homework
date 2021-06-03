package ru.itis.javalab.models;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity
@Table( name = "account")
public class User implements Serializable {

    public static final long serialVersionUID = -5443823787583700754L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String patronymic;

    private String email;
    private String hashPassword;

    @Enumerated(EnumType.STRING)
    private StateForEmail stateForEmail;

    private String confirmCode;

    public enum StateForEmail {
        CONFIRMED, NOT_CONFIRMED
    }

    @Enumerated(value = EnumType.STRING)
    private State state;

    public enum State {
        ACTIVE, BANNED
    }

    @Enumerated(value = EnumType.STRING)
    private Role role;

    public enum Role {
        USER, ADMIN
    }

    public boolean isActive() {
        return this.state == State.ACTIVE;
    }

    public boolean isBanned() {
        return this.state == State.BANNED;
    }

    public boolean isAdmin() {
        return this.role == Role.ADMIN;
    }



}


