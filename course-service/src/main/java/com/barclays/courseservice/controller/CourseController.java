package com.barclays.courseservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.barclays.courseservice.exception.CourseNotFoundException;
import com.barclays.courseservice.model.Course;
import com.barclays.courseservice.service.CourseService;

@RestController
@RequestMapping("/course")
public class CourseController {
	
	@Autowired
	private CourseService courseService;
	
	@RequestMapping(value="/register",method=RequestMethod.POST)
	public ResponseEntity<Course> registerCourse(@RequestBody Course course){
		course=courseService.registerCourse(course);
		return new ResponseEntity<Course>(course,HttpStatus.OK);
	}
	
	@RequestMapping(value="/get/{courseId}",method=RequestMethod.GET)
	public ResponseEntity<Course> getCourse(@PathVariable("courseId") int cId) throws CourseNotFoundException{
		Course course=courseService.getCourse(cId);
		return new ResponseEntity<Course>(course, HttpStatus.OK);
	}
	
	@RequestMapping(value="/getall",method=RequestMethod.GET)
	public ResponseEntity<List<Course>> getAllCourses(){
		List<Course> courses=courseService.getAllCourses();
		return new ResponseEntity<List<Course>>(courses, HttpStatus.OK);
	}
	
	@RequestMapping(value="/removecourse/{courseId}",method=RequestMethod.GET)
	public String removeCourse(@PathVariable("courseId") int courseId){
		courseService.removeCourse(courseId);
		return "The course has been removed from catalogue successfully!!!";
	}
	
}
