package com.example.demo.service;

import com.example.demo.auth.dto.UserDTO;
import com.example.demo.domain.User;

public interface UserService {
	void saveNewUser(UserDTO user);

	User mapUserDTOtoUser(UserDTO userDTO);
}
