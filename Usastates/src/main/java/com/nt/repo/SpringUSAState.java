package com.nt.repo;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nt.state.UsaState;

@Repository
public interface SpringUSAState extends JpaRepository<UsaState,Serializable>{

}
