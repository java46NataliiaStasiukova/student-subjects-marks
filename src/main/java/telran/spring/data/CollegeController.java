package telran.spring.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import telran.spring.data.model.QueryData;
import telran.spring.data.model.QueryType;
import telran.spring.data.proj.*;
import telran.spring.data.service.CollegeService;

import java.util.*;

@RestController
@RequestMapping("college")
public class CollegeController {
@Autowired
CollegeService collegeService;
private static Logger LOG = LoggerFactory.getLogger(CollegeController.class);

@GetMapping("marks")
List<MarkProj> getMarkByNameSubject(@RequestParam (name = "subject")String subject,
		@RequestParam(name = "name")String name){
	LOG.info("request for getMarkByNameSubject by subject: {}, name: {}", subject, name);
	return collegeService.getMarksByNameSubject(name, subject);
}

@GetMapping("marks/subjects")
List<StudentSubjectMark> getMarksByName(@RequestParam (name="name")String name){
	LOG.info("request for getMarksByName by name: {}", name);
	return collegeService.getMarksByName(name);
}

@GetMapping("marks/average")
List<StudentAvgMark> studentsAvgMark() {
	LOG.info("request for getting studentsAvgMark");
	return collegeService.getStudentsAvgMark();
}

@GetMapping("students/best")
List<StudentName> bestStudents(@RequestParam(defaultValue = "-1", name = "nStudents")int nStudents,
		@RequestParam(defaultValue = "", name = "subject") String subject) {
	LOG.info("request for getting bestStudents; nStudents: {}, by subject: {}", nStudents, subject);
	List<StudentName> res = null;
	if(nStudents > 0) {
		if(subject.isEmpty()) {
			res = collegeService.getTopBestStudents(nStudents);
		} else {
			res = collegeService.getTopBestStudentsSubject(nStudents, subject);
		}
	} else {
		res = collegeService.getBestStudents();
	}
	return res;
}

@GetMapping("students/worst")
List<StudentSubjectMark> worstStudents(int nStudents) {
	LOG.info("requesr for getting {} worstStudents", nStudents);
	return collegeService.getMarksOfWorstStudents(nStudents);
}

@GetMapping("marks/distribution")
List<IntervalMarksCount> marksDistribution(@RequestParam(defaultValue="10", name="interval") int interval){
	LOG.info("request for getting marksDistribution in interval: {}", interval);
	return collegeService.marksDistibution(interval);
}

@PostMapping("query")
List<String> getQuery(@RequestBody QueryData queryData){
	return queryData.type == QueryType.JPQL ? collegeService.getJpqlQuery(queryData.query) :
		collegeService.getSqlQuery(queryData.query);
}

@DeleteMapping("students")
List<String> removeStudents(@RequestParam("score") int markCountLess){
	return collegeService.removeStudents(markCountLess);
}

@DeleteMapping("subjects")
List<String> RemoveSubject(@RequestParam("score") int marksThreshold){
	return collegeService.removeLeastPopularSubjects(marksThreshold);
}

@PutMapping("marks")
void incriaseMarks(@RequestParam ("id")int studentId, @RequestParam ("delta")int delta) {
	collegeService.incriseMarksStudent(studentId, delta);
}




}
