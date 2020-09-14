package com.example.demo.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.auth.dto.UserDTO;
import com.example.demo.domain.User;
import com.example.demo.interfaces.Roles;
import com.example.demo.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public User mapUserDTOtoUser(UserDTO userDTO) {
		User user = new User();
		user.setUsername(userDTO.getUsername());
		String encodedPassword = passwordEncoder.encode(userDTO.getPassword());
		user.setPassword(encodedPassword);
		return user;
	}

	@Override
	public void saveNewUser(UserDTO user) {
		User userDomain = mapUserDTOtoUser(user);
		Set<Roles> roles = new HashSet<>();
		roles.add(Roles.USER);
		userDomain.setRoles(roles);
		userRepository.save(userDomain);
	}

}
