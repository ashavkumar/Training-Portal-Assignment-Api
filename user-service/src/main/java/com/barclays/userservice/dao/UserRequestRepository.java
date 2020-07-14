package com.barclays.userservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.barclays.userservice.model.UserRequest;

@Repository
public interface UserRequestRepository extends JpaRepository<UserRequest, Integer>{
	
}
