package com.otp.be.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.otp.be.modal.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

}
