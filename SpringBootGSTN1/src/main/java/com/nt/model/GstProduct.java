package com.nt.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Entity
@Table(name = "GstProduct")
@Data
public class GstProduct {

	      
	       private int GroupCode;
	       @Id
	       private int productcode;
	       private String productname;
	       private int preGST;
	       private int GST;
	       
	       private String fileType;
		   @Transient
		  private MultipartFile file;



}
