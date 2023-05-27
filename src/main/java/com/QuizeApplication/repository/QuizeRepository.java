package com.QuizeApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.QuizeApplication.entity.Quiz;

@Repository
public interface QuizeRepository extends JpaRepository<Quiz, Integer>  {

}
