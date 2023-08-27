package com.facebook_clone.service;

import com.facebook_clone.model.UsersModel;
import org.springframework.stereotype.Service;
import com.facebook_clone.repository.UserRepository;

@Service
public class UsersService {

    private final UserRepository usersRepository;

    public UsersService(UserRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public UsersModel registerUser(String login, String email, String password){
        if (login != null && password != null) {
            UsersModel usersModel = new UsersModel();
            usersModel.setLogin(login);
            usersModel.setEmail(email);
            usersModel.setPassword(password);
            return usersRepository.save(usersModel);
        } else {
            return null;
        }
    }

    public UsersModel authenticate(String email, String password) {
        UsersModel user = usersRepository.findByEmail(email).orElse(null);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        } else {
            return null;
        }
    }

//    private boolean isValidEmail(String email) {
//        // Define a regular expression for strong email validation
//        // This regex is a simplified version to demonstrate the concept, you can modify it to your needs
//        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
//        return email.matches(regex);
//    }
}
