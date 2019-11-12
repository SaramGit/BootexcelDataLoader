package com.nt.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.nt.model.User;

public interface SpringReadFileService {

	List<User> findAll();

	boolean saveDataFromUploadFile(MultipartFile file);

}
