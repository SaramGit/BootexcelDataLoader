package com.nt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nt.model.GstProduct;
import com.nt.service.GstProductService;

@Controller
@RequestMapping(value="/product")
public class ProductController {

	@Autowired
    private GstProductService service;
    @GetMapping("/give")
    private String home(Model model) {
  	  model.addAttribute("product", new GstProduct());
  	   return "view/product";
  	  }
    
    @PostMapping(value = "/give")
    private String uploadFile(@ModelAttribute GstProduct product,Model model) {
  	  String productname=product.getProductname();
  	  int gstrate=service.findGstRate(productname);
  	  ModelAndView model1=new ModelAndView();
  	  model.addAttribute("product", product);
      model.addAttribute("gstrate",gstrate);
  	  return "view/product";
    }
}
