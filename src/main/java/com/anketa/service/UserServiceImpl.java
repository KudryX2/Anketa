package com.anketa.service;

import com.anketa.exception.BadRequestException;
import com.anketa.exception.NotFoundException;
import com.anketa.model.User;
import com.anketa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Override
    public User getUser(String reference) {
        return userRepository.findByReference(reference)
            .orElseThrow(() -> new NotFoundException("Not Found : user not found"));
    }
}
