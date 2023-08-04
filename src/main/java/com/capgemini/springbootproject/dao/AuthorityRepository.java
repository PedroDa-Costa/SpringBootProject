package com.capgemini.springbootproject.dao;

import com.capgemini.springbootproject.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, Integer> {
    //
}
