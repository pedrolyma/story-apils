package com.storyapils.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.storyapils.model.Grade;
import com.storyapils.repository.grade.GradeRepositoryQuery;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Long>, GradeRepositoryQuery {
	
}
