package ru.itis.javalab.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.javalab.models.User;
import ru.itis.javalab.validation.ValidNames;
import ru.itis.javalab.validation.ValidPassword;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ValidNames(
        message = "{errors.invalid.names}",
        name = "firstName",
        surname = "lastName"
)
public class UserForm {
    private String firstName;
    private String lastName;

    @Email(message = "{errors.incorrect.email}")
    private String email;

    @ValidPassword(message = "{errors.invalid.password}")
    private String password;

    @Enumerated(EnumType.STRING)
    private User.StateForEmail stateForEmail;

    private String confirmCode;

    public static UserForm from(User user) {
        if (user == null) {
            return null;
        }
        return UserForm.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .password(user.getHashPassword())
//                .stateForEmail(user.getStateForEmail())
//                .confirmCode(user.getConfirmCode())
                .build();
    }

    public static List<UserForm> from(List<User> users) {
        return users.stream()
                .map(UserForm::from)
                .collect(Collectors.toList());
    }
}
