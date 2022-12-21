package com.mongo.blog.services;

import com.mongo.blog.domain.User;
import com.mongo.blog.dto.UserDto;
import com.mongo.blog.exception.ObjectNotFoundException;
import com.mongo.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado."));
    }
    
    public User insert(User user) {
        return userRepository.insert(user);
    }

    public void delete(String id) {
        User user = findById(id);
        userRepository.delete(user);
    }

    public User update(User user) {
        User newUser = findById(user.getId());
        updateData(newUser, user);

        return userRepository.save(newUser);
    }

    private void updateData(User newUser, User user) {
        newUser.setEmail(user.getEmail());
        newUser.setName(user.getName());
    }

    public User fromDto(UserDto userDto) {
        return new User(userDto.getId(), userDto.getName(), userDto.getEmail());
    }

}
