package com.nt.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Entity
@Table(name = "User")
@Data
public class User {
      
	   @Id
	   @GeneratedValue(strategy = GenerationType.AUTO)
	   private long id;
	   private String firstname;
	   private String lastname;
	   private String email;
	   private String phonenumber;
	   private String fileType;
	   @Transient
	  private MultipartFile file;
	
	   public User() {
		  System.out.println("User object");
	   }
	   public User(String firstname, String lastname, String email, String phonenumber, String fileType
			) {
		
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.phonenumber = phonenumber;
		this.fileType = fileType;
	
	}

}
