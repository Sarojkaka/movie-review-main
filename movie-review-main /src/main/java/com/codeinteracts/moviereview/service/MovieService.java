package com.codeinteracts.moviereview.service;

import java.math.BigDecimal;
import java.util.List;

import com.codeinteracts.moviereview.dto.MovieDto;
import com.codeinteracts.moviereview.entity.Movie;

public interface MovieService {

	Movie create(String name, String description, BigDecimal budget);

	List<Movie> list();

	Movie get(Long id);

	Movie update(Long id, String name, String description, BigDecimal budget);

	Movie create(MovieDto movie);
	
}
