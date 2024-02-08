package com.bruno.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruno.workshopmongo.domain.User;
import com.bruno.workshopmongo.dto.UserDTO;
import com.bruno.workshopmongo.repository.UserRepository;
import com.bruno.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	public List<User> findAll() {

		return repository.findAll();
	}

	public User findById(String id) {
		Optional<User> user = repository.findById(id);
		return user.orElseThrow(() -> new ObjectNotFoundException("notfound id"));
	}

	public User insert(User user) {

		return repository.insert(user);
	}

	public void delete(String id) {
		findById(id);
		repository.deleteById(id);
	}

	public User update(User user) {
		User updateUser = findById(user.getId());
		updateData(updateUser, user);
		
		return repository.save(updateUser);
	}

	private void updateData(User updateUser, User user) {
		updateUser.setName(user.getName());
		updateUser.setEmail(user.getEmail());
		
	}

	public User fromDTO(UserDTO userDTO) {

		return new User(userDTO.getId(), userDTO.getName(), userDTO.getEmail());
	}
}
