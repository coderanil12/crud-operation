package com.deloitte.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deloitte.demo.enitity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	
}
