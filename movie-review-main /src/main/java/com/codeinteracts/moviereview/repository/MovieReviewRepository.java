package com.codeinteracts.moviereview.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codeinteracts.moviereview.entity.MovieReview;

public interface MovieReviewRepository extends JpaRepository<MovieReview, Long> {

}
