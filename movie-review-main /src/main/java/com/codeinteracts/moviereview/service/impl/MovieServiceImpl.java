package com.codeinteracts.moviereview.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codeinteracts.moviereview.dto.MovieDto;
import com.codeinteracts.moviereview.entity.Movie;
import com.codeinteracts.moviereview.repository.MovieRepository;
import com.codeinteracts.moviereview.service.MovieService;

@Service
public class MovieServiceImpl implements MovieService {
	
	@Autowired
	private MovieRepository movieRepository;
	
	@Override
	public Movie create(MovieDto movieDTO) {
		Movie movie = create(movieDTO.getName(), movieDTO.getSynopsis(), movieDTO.getBudget());
		return movie;
	}
	
	@Override
	public Movie create(String name, String description, BigDecimal budget) {
		Movie movie = new Movie();
		movie.setName(name);
		movie.setSynopsis(description);
		movie.setBudget(budget);
		movieRepository.save(movie);
		return movie;
	}
	
	@Override
	public List<Movie> list() {
		return movieRepository.findAll();
	}

	@Override
	public Movie get(Long id) {
		return movieRepository.findById(id).get();
	}

	@Override
	public Movie update(Long id, String name, String description, BigDecimal budget) {
		Movie movie = movieRepository.findById(id).get();
		
		movie.setName(name);
		movie.setSynopsis(description);
		movie.setBudget(budget);
		movieRepository.save(movie);
		return movie;
	}


}
