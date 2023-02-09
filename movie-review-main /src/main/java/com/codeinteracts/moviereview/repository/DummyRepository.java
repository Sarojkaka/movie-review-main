package com.codeinteracts.moviereview.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codeinteracts.moviereview.entity.Dummy;

public interface DummyRepository extends JpaRepository<Dummy, Integer> {

}
