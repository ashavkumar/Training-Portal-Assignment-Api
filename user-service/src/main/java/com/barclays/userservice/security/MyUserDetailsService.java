package com.barclays.userservice.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.barclays.userservice.dao.UserRepository;
import com.barclays.userservice.model.User;
@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user=userRepository.findByUserNameForSecurity(username);
		user.orElseThrow(()->new UsernameNotFoundException("not found"));
		return user.map(MyUserDetails::new).get();
	}

}
