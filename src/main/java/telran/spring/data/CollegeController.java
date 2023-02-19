package telran.spring.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

@GetMapping("marksAvg")
List<StudentAvgMark> studentsAvgMark() {
	LOG.info("request for getting studentsAvgMark");
	return collegeService.getStudentsAvgMark();
}
//TODO
@GetMapping("students")
List<StudentName> bestStudents() {
	LOG.info("request for getting bestStudents, with no params");
	return collegeService.getBestStudents();
}
//TODO
//@GetMapping("students")
//List<StudentName> bestStudents(@RequestParam(defaultValue = "1", name = "nStudents")int nStudents) {
//	LOG.info("request for getting bestStudents; nStudents: {}", nStudents);
//	return collegeService.getTopBestStudents(nStudents);
//}
//TODO
//@GetMapping("students")
//List<StudentName> bestStudents(@RequestParam(defaultValue = "5", name = "nStudents")int nStudents,
//		@RequestParam(defaultValue = "React", name = "subject") String subject) {
//	LOG.info("request for getting bestStudents; nStudents: {}, by subject: {}", nStudents, subject);
//	return collegeService.getTopBestStudentsSubject(nStudents, subject);
//}

@GetMapping("students/marks")
List<StudentSubjectMark> worstStudents(int nStudents) {
	LOG.info("requesr for getting {} worstStudents", nStudents);
	return collegeService.getMarksOfWorstStudents(nStudents);
}

//TODO
@GetMapping("marks/interval")
List<IntervalMarksCount> marksDistribution(@RequestParam(defaultValue="10", name="interval") int interval){
	LOG.info("request for getting marksDistribution in interval: {}", interval);
	return collegeService.marksDistibution(interval);
}



}
