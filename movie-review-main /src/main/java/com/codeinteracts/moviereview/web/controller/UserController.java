package com.codeinteracts.moviereview.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.codeinteracts.enums.UserRole;
import com.codeinteracts.moviereview.dto.UserDTO;
import com.codeinteracts.moviereview.entity.User;
import com.codeinteracts.moviereview.exception.DuplicateUserNameException;
import com.codeinteracts.moviereview.exception.OTPNotVerifiedException;
import com.codeinteracts.moviereview.service.UserService;
import com.codeinteracts.moviereview.service.impl.EmailService;

@Controller
@RequestMapping("/web/user")
public class UserController {
	
	
	@Autowired
	UserService userService;
	
	@Autowired
	EmailService mailService;
	
	@GetMapping("/")
	public String list(Model model) {
		String message = (String) model.asMap().get("successMessage");
		
		model.addAttribute("successMessage", message);
		
		List<User> users = userService.list();
		model.addAttribute("users", users);
		return "user/user";
	}
	
	@GetMapping("/create")
	public String create(Model model) {
		UserDTO userDTO = new UserDTO();
		model.addAttribute("user", userDTO);
		model.addAttribute("roles", UserRole.values());
		
		return "user/user-create";
		
	}
	
	@GetMapping("/otp/verify")
	public String verifyOtp(Model model) {
		UserDTO userDTO = new UserDTO();
		model.addAttribute("user", userDTO);
		
		return "user/verify-otp";
		
	}
	
	@PostMapping("/otp/verify")
	public String verifyOtp(Model model, @ModelAttribute UserDTO userDTO) throws OTPNotVerifiedException {
		
		User user = userService.verifyOtp(userDTO);
		
		return "redirect:/web/user/";
		
	}
	
	
	@PostMapping("/create")
	public String createMovie(Model model, @ModelAttribute UserDTO userDTO, RedirectAttributes redirectAttributes) throws DuplicateUserNameException {
		List<String> errors = new ArrayList<>();
		
		if (userDTO.getUsername() == null || userDTO.getUsername().isEmpty() || userDTO.getUsername().isBlank()) {
			errors.add("Username can't be blank or empty");
		}
		
		if (userDTO.getFirstName() == null || userDTO.getFirstName().isEmpty() || userDTO.getFirstName().isBlank()) {
			errors.add("Firstname can't be blank or empty");
		}
		
		if (userDTO.getLastName() == null || userDTO.getLastName().isEmpty() || userDTO.getLastName().isBlank()) {
			errors.add("Lastname can't be blank or empty");
		}
		
		
		if(userDTO.getEmail() == null || userDTO.getEmail().isEmpty() || userDTO.getEmail().isBlank()) {
			errors.add("User email can't be blank or empty");
		}
		
		if(userDTO.getPassword() == null || userDTO.getPassword().isEmpty() || userDTO.getPassword().isBlank()) {
			errors.add("User password can't be blank or empty");
		}
		
		if(userService.get(userDTO.getUsername()) != null) {
			errors.add("Username already exists.");
		}
		
		if (errors.size() > 0) {
			model.addAttribute("errors", errors);
			model.addAttribute("user", userDTO);

			return "user/user-create";
		}
		User user = userService.create(userDTO);
		
		mailService.sendMail(user.getEmail(), user.getUsername());
		
		redirectAttributes.addFlashAttribute("successMessage", "User Added Successfully");
		return "redirect:/web/user/";
		
	}
	
	@GetMapping("/{id}/details")
	public String getMovieDetails(Model model, @PathVariable Long id) {
		User user = userService.get(id);
		model.addAttribute("user", user);
		return "user/user-detail";
		
	}
	
	@GetMapping("/{id}/update")
	public String update(Model model, @PathVariable Long id) {
		User user = userService.get(id);
		model.addAttribute("user", user);
		return "redirect:/web/movie/update";
		
	}
	
	@GetMapping("/{id}/delete")
	public String delete(Model model, @PathVariable Long id, RedirectAttributes redirectAttributes) {
		User user = userService.delete(id);
		redirectAttributes.addFlashAttribute("successMessage", "User deleted Successfully");
		return "redirect:/user/user/";
		
	}
	
	@PostMapping("/{id}/update")
	public String update(Model model, @PathVariable Long id,  @ModelAttribute UserDTO userDTO, RedirectAttributes redirectAttributes) {
		User user = userService.update(userDTO);
		model.addAttribute("user", user);
		redirectAttributes.addFlashAttribute("successMessage", "User Updated Successfully");
		return "redirect:/user/user/";
		
	}
}
