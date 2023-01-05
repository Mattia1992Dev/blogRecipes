package com.recipe.blogRecipes.repository;

import com.recipe.blogRecipes.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority , Long>{

	Optional<Authority> findByAuthorityName(String authorityName);

	boolean existsByAuthorityName(String authorityName);

	Set<Authority> findByIdIn(Set<Long> ids);




}
