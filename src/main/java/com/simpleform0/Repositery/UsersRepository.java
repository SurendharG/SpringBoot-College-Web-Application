package com.simpleform0.Repositery;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.simpleform0.model.UsersModel;

public interface UsersRepository extends JpaRepository<UsersModel,Integer>{

	Optional<UsersModel>findByLoginAndPassword(String login,String password);
}
