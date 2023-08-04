package com.capgemini.springbootproject.service;

import com.capgemini.springbootproject.entity.Authority;

import java.util.List;

public interface AuthorityService {

    List<Authority> findAll();

    Authority findById(int id);

    void save(Authority authority);
}
