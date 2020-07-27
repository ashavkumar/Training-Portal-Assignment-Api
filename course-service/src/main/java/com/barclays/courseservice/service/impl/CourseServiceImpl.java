package com.barclays.courseservice.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.barclays.courseservice.dao.CourseRepository;
import com.barclays.courseservice.exception.CourseNotFoundException;
import com.barclays.courseservice.model.Course;
import com.barclays.courseservice.service.CourseService;
@Service
public class CourseServiceImpl implements CourseService{
	
	@Autowired
	private CourseRepository courseRepository;
	
	@Override
	public Course registerCourse(Course course) {
		course.setActive(true);
		return courseRepository.save(course);
	}
	
	@Override
	public Course getCourse(int courseId) throws CourseNotFoundException {
		return courseRepository.findById(courseId).orElseThrow(()->new CourseNotFoundException("Invalid courseId,Course doesn't exist!!! "+courseId));			
	}

	@Override
	public List<Course> getAllCourses() {
		return courseRepository.findAll();
	}
	
	@Value("${server.port}")
	int port;
	@Override
	public String removeCourse(int courseId) throws CourseNotFoundException {
		Course course=getCourse(courseId);
		if(course.isActive()==true) {
			course.setActive(false);
			course=courseRepository.save(course);
			return "The course removed from catalogue successfully!!! "+port;
		}
		return "This course has already removed!!! "+port;
	}
	
}
