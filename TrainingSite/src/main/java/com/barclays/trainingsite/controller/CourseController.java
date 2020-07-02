package com.barclays.trainingsite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.barclays.trainingsite.exception.CourseNotFoundException;
import com.barclays.trainingsite.model.Course;
import com.barclays.trainingsite.model.User;
import com.barclays.trainingsite.model.UserCourse;
import com.barclays.trainingsite.response.UserResponse;
import com.barclays.trainingsite.service.CourseService;

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
	
	@RequestMapping(value="/get/courses",method=RequestMethod.GET)
	public ResponseEntity<List<Course>> getAllCourses(){
		List<Course> courses=courseService.getAllCourses();
		return new ResponseEntity<List<Course>>(courses, HttpStatus.OK);
	}
	
	@RequestMapping(value="/purchase",method=RequestMethod.POST)
	public ResponseEntity<UserResponse<Course>> purchaseCourse(@RequestBody UserCourse userCourse){
		UserResponse<Course> userResponse=courseService.purchaseCourse(userCourse);
		return new ResponseEntity<UserResponse<Course>>(userResponse, HttpStatus.OK);
	}
	
	@RequestMapping(value="/coursewisesubscription/{courseId}",method=RequestMethod.GET)
	public ResponseEntity<List<User>> courseWiseSubscription(@PathVariable("courseId") int courseId){
		List<User> listOfUser=courseService.courseWiseSubscription(courseId);
		return new ResponseEntity<List<User>>(listOfUser,HttpStatus.OK);
	}
	/*
	 * @RequestMapping(value="/delete/{courseId}",method = RequestMethod.DELETE)
	 * public ResponseEntity<String> deleteCourse(@PathVariable("courseId") int
	 * courseId){ courseService.deleteCourse(courseId); return new
	 * ResponseEntity<String>("Successfully deleted!!!",HttpStatus.OK); }
	 * 
	 * @RequestMapping(value="/update/{courseId}",method=RequestMethod.PUT) public
	 * ResponseEntity<String> updateCourse(@PathVariable("courseId") int
	 * courseId,@RequestBody Course course) throws CourseNotFoundException{
	 * courseService.updateCourse(courseId,course); return new
	 * ResponseEntity<String>("Your course has been updated successfully",HttpStatus
	 * .OK); }
	 */
}
