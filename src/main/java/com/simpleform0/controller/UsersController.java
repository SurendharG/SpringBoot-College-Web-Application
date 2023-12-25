package com.simpleform0.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.simpleform0.model.UsersModel;
import com.simpleform0.service.UsersService;

@Controller
public class UsersController {	

	private final UsersService usersService;
		
    @Autowired
	public UsersController(UsersService usersService) {
		this.usersService = usersService;
	}
	@GetMapping("/register")
	public String getRegisterPage(Model model) {
		model.addAttribute("registerRequest",new UsersModel ());
		return "register_page";
	}
	@PostMapping("/register")
	public String register(@ModelAttribute UsersModel usersModel) {
		System.out.println("register request : "+usersModel);
		UsersModel registeredUser=usersService.registerUser(usersModel.getLogin(),usersModel.getEmail(),usersModel.getPassword());
		return registeredUser == null ? "error_page":"redirect:/login";
	}
	@GetMapping("/login")
	public String getloginPage(Model model) {
		model.addAttribute("loginRequest",new UsersModel());
		return "login_page";
		
	}
	@PostMapping("/login")
	public String login(@ModelAttribute UsersModel usersModel,Model model) {
		System.out.println("login request : "+usersModel);
		UsersModel authenticated=usersService.authenticate(usersModel.getLogin(),usersModel.getPassword());
		if(authenticated != null) {
			model.addAttribute("userLogin",authenticated.getLogin());
			return "personal_page";
		}
		else {
			return "error_page";
		}		
	}
}
