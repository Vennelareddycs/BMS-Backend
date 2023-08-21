package com.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.model.User;
import com.user.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepo;

    private static final String EMAIL_FORMAT = "[a-z0-9]+@[a-z]+\\.[a-z]{2,3}";
    private static final String PASSWORD_FORMAT = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,20}$";
    private static final String USERNAME_FORMAT = "^(?=[a-zA-Z0-9._]{3,20}$)(?!.*[_.]{2})[^_.].*[^_.]$";

    public String registerUser(User user) {
        User existingUserByEmail = userRepo.findByEmail(user.getEmail());
        if (existingUserByEmail != null) {
            return "Email already exists. Please try again with a different email.";
        }

        if (!user.getEmail().matches(EMAIL_FORMAT)) {
            return "Invalid email format.";
        }

        if (!user.getPassword().matches(PASSWORD_FORMAT)) {
            return "Invalid password format.";
        }

        if (!user.getUsername().matches(USERNAME_FORMAT)) {
            return "Invalid username format.";
        }

        User existingUserByUsername = userRepo.findByUsername(user.getUsername());
        if (existingUserByUsername != null) {
            return "Username already exists. Please try again with a different username.";
        }

        userRepo.save(user);
        return "User registered successfully!";
    }


	public String loginUser(String email, String password) {
		 User user = userRepo.findByEmail(email);
		   if (user == null) {
	            return "Login failed. User not found. Email: " + email;
	        }
	        
			if (!user.getPassword().equals(password)) {
			    return "Incorrect password.";
			}
	        return "Successful login.";
	    }

	
}
