package com.codeinteracts.moviereview.web.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.codeinteracts.moviereview.dto.MovieDto;
import com.codeinteracts.moviereview.entity.Movie;
import com.codeinteracts.moviereview.service.MovieService;

@Controller
@RequestMapping("/web/movie")
public class MovieController {
	
	@Autowired
	MovieService movieService;
	
	@GetMapping("/")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public String list(Model model) {
		String message = (String) model.asMap().get("successMessage");
		
		model.addAttribute("successMessage", message);
		
		List<Movie> movies = movieService.list();
		model.addAttribute("movies", movies);
		return "movie/movie";
	}
	
	@GetMapping("/create")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public String create(Model model) {
		MovieDto movie = new MovieDto();
		model.addAttribute("movie", movie);
		
		return "movie/movie-create";
		
	}
	
	@PostMapping("/createDTO")
	public String createMovie(Model model, @Validated @ModelAttribute MovieDto movieDTO, RedirectAttributes redirectAttributes) {
		List<String> errors = new ArrayList<>();
		
		if (movieDTO.getName() == null || movieDTO.getName().isEmpty() || movieDTO.getName().isBlank()) {
			errors.add("Movie name can't be blank or empty");
		}
		
		if (errors.size() > 0) {
			model.addAttribute("errors", errors);
			model.addAttribute("movie", movieDTO);

			model.addAttribute("hello", "hello");
			return "movie/movie-create";
		}
		Movie movie = movieService.create(movieDTO);
		
		redirectAttributes.addFlashAttribute("successMessage", "Movie Added Successfully");
		return "redirect:/web/movie/";
		
	}
	
	@PostMapping("/create")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public String createMovie(@RequestParam String name, @RequestParam String description, @RequestParam(required = false) BigDecimal budget,Model model,  RedirectAttributes redirectAttributes) {
		List<String> errors = new ArrayList<>();
		
		if (name == null || name.isEmpty() || name.isBlank()) {
			errors.add("Movie name can't be blank or empty");
		}
		
		if (errors.size() > 0) {
			model.addAttribute("errors", errors);
			model.addAttribute("description", description);
			model.addAttribute("name", name);
			model.addAttribute("budget", budget);

			model.addAttribute("hello", "hello");
			return "movie/movie-create";
		}
		
		Movie movie = movieService.create(name, description, budget);
		
		redirectAttributes.addFlashAttribute("successMessage", "Movie Added Successfully");
		return "redirect:/web/movie/";
		
	}
	
	@GetMapping("/{id}/details")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public String getMovieDetails(Model model, @PathVariable Long id) {
		Movie movie = movieService.get(id);
		model.addAttribute("movie", movie);
		return "movie/movie-detail";
		
	}
	
	@GetMapping("/{id}/update")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public String update(Model model, @PathVariable Long id) {
		Movie movie = movieService.get(id);
		model.addAttribute("movie", movie);
		return "redirect:/web/movie/update";
		
	}
	
	@PostMapping("/{id}/update")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public String update(Model model, @PathVariable Long id, @RequestParam String name, @RequestParam String description, @RequestParam BigDecimal budget) {
		Movie movie = movieService.update(id, name, description, budget);
		model.addAttribute("movie", movie);
		return "redirect:/web/movie/";
		
	}
	

}
