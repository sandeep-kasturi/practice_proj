package com.spring.cache.service;

import java.util.List;

import com.spring.cache.entity.User;

public interface UserService {

	void add(User user);

	List<User> getAll();

	User getById(Long id) throws Exception ;

	void deleteById(Long id);

	void updateById(Long id, User user) throws Exception;

}
