package com.capgemini.cappay.service;

import com.capgemini.cappay.dto.UserDto;
import com.capgemini.cappay.model.User;
import com.capgemini.cappay.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User save(UserDto userDto) {

        User user = new User();
        user.setName(userDto.getName());
        user.setCreationDate(new Date());

        return userRepository.save(user);
    }

    public User getById(Long id) {
        return userRepository.getById(id);
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }
}
