package telran.spring.data;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import jakarta.annotation.PreDestroy;
import telran.spring.data.util.JPQLQueryConsole;

@SpringBootApplication
public class StudentsSubjectsMarksApplication {
	
	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(StudentsSubjectsMarksApplication.class,  args);
		      JPQLQueryConsole console = ctx.getBean(JPQLQueryConsole.class);
		      console.run();
		      ctx.close();
	}
	
	@PreDestroy
	void preDestroy() {
		System.out.println("bye - shutdown has been performed");
	}

}
