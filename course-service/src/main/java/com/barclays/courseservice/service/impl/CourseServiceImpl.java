package com.barclays.courseservice.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
		course=courseRepository.save(course);
		return course;
	}
	
	@Override
	public Course getCourse(int courseId) throws CourseNotFoundException {
		Course course=courseRepository.findById(courseId).orElseThrow(()->new CourseNotFoundException("Invalid course id "+courseId));
		return course;			
	}

	@Override
	public List<Course> getAllCourses() {
		return courseRepository.findAll();
	}
	
	@Override
	public void removeCourse(int courseId) {
		Course course=courseRepository.findById(courseId).get();
		course.setActive(false);
		course=courseRepository.save(course);
	}
	
	
	@Override
	public void updateCourse(int courseId,Course course) throws CourseNotFoundException {
		Course retrieveCourse=courseRepository.findById(courseId).orElseThrow(()->new CourseNotFoundException("Invalid Course Id "+courseId));
		if(course.getCourseName()!=null)
			retrieveCourse.setCourseName(course.getCourseName());
		if(course.getCourseType()!=null)
			retrieveCourse.setCourseType(course.getCourseType());
		if(course.getCourseDiscription()!=null)
			retrieveCourse.setCourseDiscription(course.getCourseDiscription());
		retrieveCourse.setActive(true);
		retrieveCourse.setCourseCost(course.getCourseCost());
		courseRepository.save(retrieveCourse);
	}
}
