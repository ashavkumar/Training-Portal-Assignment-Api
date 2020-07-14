package com.barclays.courseservice.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

	/*
	 * @Autowired UserRepository userRepository;
	 */

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//Optional<User> user=userRepository.findByUserNameForSecurity(username);
		//user.orElseThrow(()->new UsernameNotFoundException("not found"));
		//return user.map(MyUserDetails::new).get();
		return null;
	}

}
