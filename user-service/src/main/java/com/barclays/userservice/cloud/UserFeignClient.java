package com.barclays.userservice.cloud;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.barclays.userservice.model.Course;



@FeignClient(name="course-service")
public interface UserFeignClient {

	@PostMapping("/course/register")
	public Course registerCourse(Course course);
	@GetMapping("/course/get/{courseId}")
	public Course getCourse(@PathVariable("courseId") int cId);
	@GetMapping("/course/getall")
	public List<Course> getAllCourses();
	@GetMapping("/course/removecourse/{courseId}")
	public String removeCourse(@PathVariable("courseId") int courseId);
	
}
