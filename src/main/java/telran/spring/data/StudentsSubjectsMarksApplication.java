package telran.spring.data;

import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import jakarta.annotation.PreDestroy;

@SpringBootApplication
public class StudentsSubjectsMarksApplication {
private static final Object SHUTDOWN = "shutdown";
	
	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(StudentsSubjectsMarksApplication.class,  args);
		
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		while(true) {
			System.out.println("To stop server type " + SHUTDOWN);
			String line = scanner.nextLine();
			if (line.equals(SHUTDOWN)) {
				break;
			}
		}
		ctx.close();
	}
	
	@PreDestroy
	void preDestroy() {
		System.out.println("bye - shutdown has been performed");
	}

}
