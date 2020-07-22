package com.barclays.userservice.cloud;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.barclays.userservice.model.Course;


@FeignClient(url = "http://localhost:9002/course",name = "Course-Service")
public interface UserFeignClient {

	@PostMapping("/register")
	public Course registerCourse(Course course);
	@GetMapping("/get/{courseId}")
	public Course getCourse(@PathVariable("courseId") int cId);
	@GetMapping("/getall")
	public List<Course> getAllCourses();
	@GetMapping("/removecourse/{courseId}")
	public String removeCourse(@PathVariable("courseId") int courseId);
	
}
