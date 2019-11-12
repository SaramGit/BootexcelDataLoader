package com.nt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nt.model.User;
import com.nt.service.SpringReadFileService;

@Controller
public class SpringReadFileController {

	      @Autowired
	      private SpringReadFileService service;
	      @GetMapping("/")
	      private String home(Model model) {
	    	  model.addAttribute("user", new User());
	    	  List<User> users=service.findAll();
	    	  model.addAttribute("users",users);
	    	  return "view/users";
	    	  }
	      
	      @PostMapping(value = "/fileupload")
	      private String uploadFile(@ModelAttribute User user,RedirectAttributes attributes) {
	    	  boolean isflag=service.saveDataFromUploadFile(user.getFile());
	    	         if (isflag) {
				           attributes.addAttribute("SuccessMessage", "File Uploaded successfully.............");
			         }
	    	         else {
	    	        	  attributes.addAttribute("ErrorMessage", "File Upload Failed Try again later..........");
					}
	    	         
	    	  return "redirect:/";
	      }
}
