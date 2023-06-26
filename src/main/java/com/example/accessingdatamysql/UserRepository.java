package com.example.accessingdatamysql;

import org.springframework.data.repository.CrudRepository;

import com.example.accessingdatamysql.UserModel;

public interface UserRepository extends CrudRepository<UserModel, Integer> {}
