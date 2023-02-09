package com.codeinteracts.moviereview.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.codeinteracts.moviereview.entity.Movie;
import com.codeinteracts.moviereview.service.MovieService;

@Controller
public class MovieAppController {

	@Autowired
	MovieService movieService;

	@RequestMapping("/")
	public String view(Model model) {
		String message = (String) model.asMap().get("successMessage");

		model.addAttribute("successMessage", message);

		List<Movie> movies = movieService.list();
		model.addAttribute("movies", movies);
		return "movie/movie";
	}

}
