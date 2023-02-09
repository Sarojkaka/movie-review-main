package com.codeinteracts.moviereview.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

import org.hibernate.annotations.ColumnDefault;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Movie implements Serializable {

	private static final long serialVersionUID = -8084777722578307223L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	private Float aggregatedRating;
	
	private BigDecimal budget;
	
	private String synopsis;
	
	@ColumnDefault("true")
	private Boolean active;
	
	@OneToMany(targetEntity=MovieReview.class, mappedBy="movie", cascade=CascadeType.ALL, fetch = FetchType.LAZY) 
    private Set<MovieReview> reviews;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Float getAggregatedRating() {
		return aggregatedRating;
	}

	public void setAggregatedRating(Float aggregatedRating) {
		this.aggregatedRating = aggregatedRating;
	}

	public BigDecimal getBudget() {
		return budget;
	}

	public void setBudget(BigDecimal budget) {
		this.budget = budget;
	}

	public String getSynopsis() {
		return synopsis;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}


}
