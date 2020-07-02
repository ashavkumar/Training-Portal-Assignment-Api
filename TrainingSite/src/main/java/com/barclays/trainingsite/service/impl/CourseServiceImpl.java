package com.barclays.trainingsite.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.barclays.trainingsite.dao.CourseRepository;
import com.barclays.trainingsite.dao.UserCourseRepository;
import com.barclays.trainingsite.dao.UserRepository;
import com.barclays.trainingsite.exception.CourseNotFoundException;
import com.barclays.trainingsite.model.Course;
import com.barclays.trainingsite.model.User;
import com.barclays.trainingsite.model.UserCourse;
import com.barclays.trainingsite.response.UserResponse;
import com.barclays.trainingsite.service.CourseService;
@Service
public class CourseServiceImpl implements CourseService{
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private UserCourseRepository userCourseRepository;
	
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
	public UserResponse<Course> purchaseCourse(UserCourse userCourse) {
		//int userId=userCourse.getUserId();
		//int courseId=userCourse.getCourseId();
		User user=userRepository.findById(userCourse.getUserId()).get();
		Course course=courseRepository.findById(userCourse.getCourseId()).get();
		Set<Course> setOfCourse=user.getSetOfCourse();
		if(setOfCourse.add(course)==true  && course.isActive()==true) {
			user.setSetOfCourse(setOfCourse);
			userRepository.save(user);
			userCourse.setStatus("APPROVED");
			userCourseRepository.save(userCourse);
			return new UserResponse<>("You purchased this course successfully!!!",course);
		}
		else {
			return new UserResponse<>("Already you have purchased it!!!",null);
		}
		/*
		 * UserCourse
		 * retrievedUserCourse=userCourseRepository.findInstance(userId,courseId);
		 * if(retrievedUserCourse==null) { Course
		 * course=courseRepository.findById(userCourse.getCourseId()).get(); User
		 * user=userRepository.findById(userCourse.getUserId()).get(); List<Course>
		 * list=user.getListOfCourse(); list.add(course); user.setListOfCourse(list);
		 * userRepository.save(user); userCourse.setStatus("APPROVED");
		 * userCourseRepository.save(userCourse); return new
		 * UserResponse<>("You purchased this course successfully!!!",course); } else {
		 * return new UserResponse<>("Already you have purchased it!!!",null); }
		 */
	}
	
	@Override
	public List<User> courseWiseSubscription(int courseId) {
		List<User> listOfUser=new ArrayList<>();
		List<Integer> listOfUserId=userCourseRepository.courseWiseSubscription(courseId);
		for(int i:listOfUserId) {
			User user=userRepository.findById(i).get();
			listOfUser.add(user);
		}
		return listOfUser;
	}
	@Override
	public void deleteCourse(int courseId) {
		int count=userCourseRepository.countUserId(courseId);
		if(count==0)
			courseRepository.deleteById(courseId);
		else {
			
		}
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
