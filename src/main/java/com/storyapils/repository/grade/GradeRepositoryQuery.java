package com.storyapils.repository.grade;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.storyapils.model.Grade;
import com.storyapils.repository.filter.GradeFilter;

public interface GradeRepositoryQuery {
	
	public Page<Grade> filtrar(GradeFilter gradefilter, Pageable pageable); 

}
