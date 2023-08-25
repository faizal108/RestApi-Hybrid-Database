package com.faizal.HybridDatabase.service;

import com.faizal.HybridDatabase.model.User;
import com.faizal.HybridDatabase.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    public void createUser(User user) {
        userRepository.save(user);
    }
    public List getUser() {
        return (List) userRepository.findAll();
    }
    public Optional findById(long id) {
        return userRepository.findById(id);
    }
    public User update(User user, long l) {
        return userRepository.save(user);
    }
    public void deleteUserById(long id) {
        userRepository.deleteById(id);
    }
    public User updatePartially(User user, long id) {
        Optional<User> usr = findById(id);
        User newuser = usr.get();
        newuser.setCountry(user.getCountry());
        return userRepository.save(newuser);
    }
}
