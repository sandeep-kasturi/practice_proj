package com.spring.cache.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.cache.entity.User;
import com.spring.cache.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void add(User user) {
		userRepository.save(user);
	}

	@Override
	public List<User> getAll() {
		
		return userRepository.findAll();
	}

	@Override
	public User getById(Long id) throws Exception {
		User user = userRepository.findById(id).get();
		if(user == null) {
			throw new Exception("user not found with provided id");
		}
		return user;
	}

	@Override
	public void deleteById(Long id) {
		userRepository.deleteById(id);
	}

	@Override
	public void updateById(Long id, User user) throws Exception{
		User dbuser = userRepository.findById(id).get();
		if(user == null) {
			throw new Exception("User not found with the provided id");
		}
		dbuser.setName(user.getName());
		userRepository.save(dbuser);
	}

}
