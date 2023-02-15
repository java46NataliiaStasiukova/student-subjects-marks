package telran.spring.data;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Component;
import jakarta.annotation.PostConstruct;
import telran.spring.data.model.*;
import telran.spring.data.repo.MarkRepository;
import telran.spring.data.repo.StudentRepository;
import telran.spring.data.repo.SubjectRepository;
import telran.spring.data.service.CollegeService;

@Component
public class RandonDbCreation {
@Value("${app..marks.amount:100}")
private int MARKS_LIMIT;
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
private static Logger LOG = LoggerFactory.getLogger(RandonDbCreation.class);
@Autowired
StudentRepository studentRepository;
@Autowired
SubjectRepository subjectRepository;
@Autowired
MarkRepository markRepository;

@PostConstruct
void dbCreation() {
	new Random().ints(0, names.length).distinct()
	.limit(names.length).forEach(e -> 
	collegeService.addStudent(new Student(count++, names[e])));
	LOG.debug("created students: {}", studentRepository.findAll().size());
	count = 1;
	
	new Random().ints(0, subjects.length).distinct()
	.limit(subjects.length).forEach(e -> 
	collegeService.addSubject(new Subject(count++, subjects[e])));
	LOG.debug("created subjects: {}", subjectRepository.findAll().size());
	
	new Random().ints(MIN, MAX)
	.limit(MARKS_LIMIT).forEach(e -> 
	collegeService.addMark(new Mark(getRandomId(names.length),
			getRandomId(subjects.length), e)));
	LOG.debug("created marks: {}", markRepository.findAll().size());
}
private long getRandomId(int length) {

	return ThreadLocalRandom.current().nextInt(1, length);
}

}
