package ru.kpfu.itis.transportsem.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.kpfu.itis.transportsem.validation.ValidEmail;
import ru.kpfu.itis.transportsem.validation.ValidNames;
import ru.kpfu.itis.transportsem.validation.ValidPassword;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

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
    @NotEmpty(message = "{errors.empty_field.first_name}")
    private String firstName;

    @NotEmpty(message = "{errors.empty_field.last_name}")
    private String lastName;

    @ValidEmail(message = "{errors.incorrect.email}")
    private String email;

    @ValidPassword(message = "{errors.invalid.password}")
    private String password;
}