package telran.spring.data;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Component;
import jakarta.annotation.PostConstruct;
import telran.spring.data.model.*;
import telran.spring.data.service.CollegeService;

@Component
public class RandomDbCreation {
@Value("${app..marks.amount:100}")
private int MARKS_LIMIT;
@Value("${spring.jpa.hibernate.ddl-auto:create}")
String ddlAutoProps;
private static final int MIN = 35;
private static final int MAX = 100;
@Autowired
CollegeService collegeService;
String subjects[] = { "Java core",
		"Java Technologies", "Spring Data",
		"Spring Security", "Spring Cloud", "CSS",
		"HTML", "JS", "React", "Material-UI" }; 
String names[] = { "Abraham", "Sarah",
		"Itshak", "Rahel", "Asaf", "Yacob", "Rivka",
		"Yosef", "Benyanim", "Dan","Ruben", "Moshe",
		"Aron", "Yehashua", "David", "Salomon",
		"Nefertity", "Naftaly", "Natan", "Asher" };
int count = 1;
private static Logger LOG = LoggerFactory.getLogger(RandomDbCreation.class);

@PostConstruct
void dbCreation() {
	if (ddlAutoProps.equals("create")) {
		LOG.info("creating new DB with {} ramdom marks", MARKS_LIMIT);
		new Random().ints(0, names.length).distinct().limit(names.length)
				.forEach(e -> collegeService.addStudent(new Student(count++, names[e])));
		count = 1;
		new Random().ints(0, subjects.length).distinct().limit(subjects.length)
				.forEach(e -> collegeService.addSubject(new Subject(count++, subjects[e])));
		new Random().ints(MIN, MAX).limit(MARKS_LIMIT).forEach(
				e -> collegeService.addMark(new Mark(getRandomId(names.length), getRandomId(subjects.length), e)));
		LOG.info("DB was created with {} ramdom marks", MARKS_LIMIT);
	} else {
		LOG.info("new DB with {} marks was NOT created", MARKS_LIMIT);
	}
}
private long getRandomId(int length) {

	return ThreadLocalRandom.current().nextInt(1, length);
}

}
