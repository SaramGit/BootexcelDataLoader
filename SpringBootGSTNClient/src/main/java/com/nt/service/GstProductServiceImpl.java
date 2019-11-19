package com.nt.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.nt.dao.GSTNRepo;
import com.nt.model.GstProduct;

@Service
public class GstProductServiceImpl implements GstProductService {
    
	@Autowired
	private GSTNRepo repo;
	
	@Override
	public int findGstRate(String productname) {
		List<GstProduct> list=new ArrayList<>();
        Iterable<GstProduct> product=repo.findAll();
        product.forEach(list::add);
        Predicate<GstProduct> prod=pro->pro.getProductname().equalsIgnoreCase(productname);
    	int rate=0;
		/*list.forEach((rate)->{
			  printRate(()->rate.getGst());
		});*/
        for(GstProduct product2:list) {
        	if(prod.test(product2)) {
        		  rate=product2.getGST();
        		  System.out.println(rate);
        	}
        }
      
       // Spliterator<GstProduct> spliterator = product.spliterator();   
        //Stream<GstProduct> stream=StreamSupport.stream(spliterator,false);
     
      //  Predicate<GstProduct> productnPredicate = f->f.equalsIgnoreCase(productname);
	   /* stream.forEach(new Consumer<GstProduct>() { 
		
		   @Override
		   public void accept(GstProduct product) 
		   { 
			   
			     product.getProductname().equalsIgnoreCase(productname)
		           
		   } 
		
		});*/
		//stream.map(p->p.getProductname().equalsIgnoreCase(productname)).reduce(true,GstProduct::getGST:"null");
	  
        // stream.filter(productnPredicate.test(productname))
        /*product.forEach(new Consumer<GstProduct>() { 
		
		   @Override
		   public void accept(GstProduct product) 
		   { 
		           Predicate<product> p=p->p.
		       
		   } 
		
		}); 
		*/
        
		return rate;
	}

	private void printRate(Object object) {
		
	}


	

}
