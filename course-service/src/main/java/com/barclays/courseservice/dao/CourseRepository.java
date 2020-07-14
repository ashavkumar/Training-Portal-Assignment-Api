package com.barclays.courseservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.barclays.courseservice.model.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer>{

}
