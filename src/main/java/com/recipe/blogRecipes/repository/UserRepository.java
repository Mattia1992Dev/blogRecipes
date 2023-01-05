package com.recipe.blogRecipes.repository;

import com.recipe.blogRecipes.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username); //sono sempre SELECT che interrogano il Db ad una sola tabella

    Boolean existsByUsername(String username);
}
