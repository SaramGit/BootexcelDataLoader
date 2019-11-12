package com.nt.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nt.model.GstProduct;

@Repository
public interface GSTNRepo extends CrudRepository<GstProduct, Integer>{

}
