package com.barclays.trainingsite.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.barclays.trainingsite.model.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer>{

}
