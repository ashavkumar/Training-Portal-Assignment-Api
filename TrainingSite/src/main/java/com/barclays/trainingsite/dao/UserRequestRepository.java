package com.barclays.trainingsite.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.barclays.trainingsite.model.UserRequest;

@Repository
public interface UserRequestRepository extends JpaRepository<UserRequest, Integer>{
	
}
