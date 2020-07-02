package com.barclays.trainingsite.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.barclays.trainingsite.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

	@Query(value="SELECT * FROM USER WHERE User_Name=?",nativeQuery = true) 
	public User findByUserName(String userName);
	 
	@Query(value="SELECT * FROM USER WHERE User_Name=?",nativeQuery = true) 
	public Optional<User> findByUserNameForSecurity(String userName);
	 
}
