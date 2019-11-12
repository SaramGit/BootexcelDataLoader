package com.nt.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nt.model.User;

@Repository
public interface SpringReadFileRepository extends CrudRepository<User, Long> {

}
