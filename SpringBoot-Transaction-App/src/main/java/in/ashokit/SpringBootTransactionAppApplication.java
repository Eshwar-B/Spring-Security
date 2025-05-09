package in.ashokit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import in.ashokit.service.EmpService;

@SpringBootApplication
public class SpringBootTransactionAppApplication {

	public static void main(String[] args) {
		
		ConfigurableApplicationContext context = SpringApplication.run(SpringBootTransactionAppApplication.class, args);
		
		
		EmpService bean = context.getBean(EmpService.class);
		
		bean.saveEmp();
		
	}

}
