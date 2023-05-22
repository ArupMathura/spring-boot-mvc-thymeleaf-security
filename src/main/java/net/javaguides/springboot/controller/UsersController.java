package net.javaguides.springboot.controller;

import net.javaguides.springboot.dto.UserRegistrationDto;
import net.javaguides.springboot.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
@Controller
public class UsersController {

    private UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String listRegisteredUsers(Model model){
        List<UserRegistrationDto> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "users";
    }
}
