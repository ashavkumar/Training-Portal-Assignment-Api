package com.barclays.userservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.barclays.userservice.model.CourseRequest;

@Repository
public interface CourseRequestRepository extends JpaRepository<CourseRequest, Integer>{

}
