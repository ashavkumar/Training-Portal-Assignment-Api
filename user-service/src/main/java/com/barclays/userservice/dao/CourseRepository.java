package com.barclays.userservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.barclays.userservice.model.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer>{

}
