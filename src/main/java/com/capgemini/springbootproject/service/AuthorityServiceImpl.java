package com.capgemini.springbootproject.service;

import com.capgemini.springbootproject.dao.AuthorityRepository;
import com.capgemini.springbootproject.entity.Authority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorityServiceImpl implements AuthorityService{

    private AuthorityRepository authorityRepository;

    @Autowired
    public AuthorityServiceImpl(AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }

    @Override
    public List<Authority> findAll() {
        return authorityRepository.findAll();
    }

    @Override
    public Authority findById(int id) {
        Optional<Authority> result = authorityRepository.findById(id);
        Authority authority;
        if(result.isPresent()) {
            authority = result.get();
        } else {
            throw new RuntimeException("Did not find authority id: " + id);
        }
        return authority;
    }

    @Override
    public void save(Authority authority) {
        authorityRepository.save(authority);
    }
}
