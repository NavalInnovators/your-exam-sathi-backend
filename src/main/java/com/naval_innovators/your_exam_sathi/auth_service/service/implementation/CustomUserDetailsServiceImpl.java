package com.naval_innovators.your_exam_sathi.auth_service.service.implementation;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.naval_innovators.your_exam_sathi.auth_service.models.User;
import com.naval_innovators.your_exam_sathi.auth_service.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsServiceImpl implements UserDetailsService {

	private final UserRepository userRepository;
	
	
	@Override
    public UserDetails loadUserByUsername(String identifier) throws UsernameNotFoundException {
        // Load user by either username or email
        User user = userRepository.findByUserNameOrEmail(identifier, identifier)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username or email: " + identifier));
        
        return new CustomUserDetails(user);
    }
}
