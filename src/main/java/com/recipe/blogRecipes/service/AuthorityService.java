package com.recipe.blogRecipes.service;

import com.recipe.blogRecipes.entity.Authority;
import com.recipe.blogRecipes.repository.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class AuthorityService {

    @Autowired
    AuthorityRepository authorityRepository;

    public Optional<Authority> findById(long id){
        return authorityRepository.findById(id);
    }

    public Optional<Authority> findByAuthorityName(String authorityName){
        return authorityRepository.findByAuthorityName(authorityName);
    }

    public void save(Authority authority){
        authorityRepository.save(authority);
    }

    public boolean existsByAuthorityName(String authorityName){
        return authorityRepository.existsByAuthorityName(authorityName);
    }

    public List<Authority> findAll(){
       return authorityRepository.findAll();
    }

    public Set<Authority> findByIdIn(Set<Long> ids){
        return authorityRepository.findByIdIn(ids);
    }

}
