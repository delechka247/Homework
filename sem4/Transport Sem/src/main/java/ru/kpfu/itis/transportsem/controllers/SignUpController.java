package ru.kpfu.itis.transportsem.controllers;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.kpfu.itis.transportsem.dto.EmailPasswordDto;
import ru.kpfu.itis.transportsem.dto.JwtDto;
import ru.kpfu.itis.transportsem.dto.UserDto;
import ru.kpfu.itis.transportsem.dto.UserForm;
import ru.kpfu.itis.transportsem.services.SignUpServiceImpl;
import javax.validation.Valid;
import java.util.Objects;


@RestController
public class SignUpController {

    @Autowired
    private SignUpServiceImpl signUpServiceImpl;

    @ApiOperation(value = "Регистрация пользователя")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Успешная регистрация", response = UserDto.class)})
    @PostMapping("/signUp")
    public UserDto addUser(@Valid @RequestBody UserForm form) {
        return signUpServiceImpl.add(form);
    }


//    @GetMapping("/signUp")
//    public String getSignUpPage(Model model) {
//        model.addAttribute("userForm", new UserForm());
//        return "sign_up";
//    }

//    @PostMapping("/signUp")
//    public String addUser(@Valid UserForm form, BindingResult bindingResult, Model model) {
//
//        if (bindingResult.hasErrors()) {
//            bindingResult.getAllErrors().stream().anyMatch(error -> {
//                if (Objects.requireNonNull(error.getCodes())[0].equals("userForm.ValidNames")) {
//                    model.addAttribute("namesErrorMessage", error.getDefaultMessage());
//                }
//                return true;
//            });
//            model.addAttribute("userForm", form);
//            return "sign_up";
//        }
//
//
//        signUpServiceImpl.add(form);
//        return "redirect:/signIn";
//    }
}
