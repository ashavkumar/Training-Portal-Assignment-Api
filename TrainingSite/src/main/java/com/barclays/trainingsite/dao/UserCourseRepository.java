package com.barclays.trainingsite.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.barclays.trainingsite.model.UserCourse;

@Repository
public interface UserCourseRepository extends JpaRepository<UserCourse, Integer>{

	@Query(value = "SELECT * FROM USER_COURSE WHERE User_Id=?1 AND Course_Id=?2",nativeQuery = true)
	public UserCourse findInstance(int userId, int courseId);
	@Query(value="SELECT User_Id FROM USER_COURSE WHERE Course_Id=?",nativeQuery = true)
	public List<Integer> courseWiseSubscription(int courseId);
	@Query(value="SELECT Course_Id FROM USER_COURSE WHERE User_Id=?",nativeQuery = true)
	public List<Integer> userWiseSubscription(int userId);
	@Query(value="SELECT COUNT(Course_Id) FROM USER_COURSE where Course_Id=?1 ",nativeQuery = true)
	public int countUserId(int courseId);
}
