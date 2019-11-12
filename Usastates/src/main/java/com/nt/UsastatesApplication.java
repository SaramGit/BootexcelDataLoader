package com.nt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;

import com.nt.config.AppConfig;
import com.nt.service.USAStatesevice;

@SpringBootApplication
@Import(AppConfig.class)
public class UsastatesApplication {

	public static void main(String[] args) {
		ApplicationContext ctx=null;
		USAStatesevice serStatesevice=null;
		ctx=SpringApplication.run(UsastatesApplication.class, args);
		serStatesevice=(USAStatesevice)ctx.getBean("stservice",USAStatesevice.class);
		System.out.println(serStatesevice.RegisterState());
		
		/*ApplicationContext ctx = SpringApplication.run(UsastatesApplication.class, args);
		CustemerService service = (CustemerService) ctx.getBean("ser");
		Custemer c = new Custemer();
		
		  c.setPNAME("Bikash");
		  
		  c.setPRICE(1212); boolean result = service.Save(c);
		  
		  System.out.println("The Result is" + result);*/
	}

}
