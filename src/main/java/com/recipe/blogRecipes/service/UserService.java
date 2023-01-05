package com.recipe.blogRecipes.service;

import com.recipe.blogRecipes.entity.User;
import com.recipe.blogRecipes.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service //Nel Service faccio la logica di business. Interrogo il Db che mi restituisce qualcosa e se voglio lo elaboro
public class UserService { // @Service classe richiamabile da altre classi. Fondamentale avere autowired.
	
	@Autowired
	UserRepository userRepository; //Annotazione di Spring serve a inniettare all'interno della classe il repository
	           									//fa dependency inniection
	
	public boolean existsByUsername(String username) {return userRepository.existsByUsername(username);
	}


	public void save(User user) {
		userRepository.save(user);
	}

    public Optional<User> findById(long id) {
		return userRepository.findById(id);
    }

	public Optional<User> findByUsername(String username) { return userRepository.findByUsername(username); }
	public Optional<User> findByUsernameOrEmail(String username, String email) { return userRepository.findByUsername(username); }

}
