package telran.spring.data;

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
@GetMapping("marks")
List<MarkProj> getMarkByNameSubject(@RequestParam (name = "subject")String subject,
		@RequestParam(name = "name")String name){
	
	return collegeService.getMarksByNameSubject(name, subject);
}
@GetMapping("marks/subjects")
List<StudentSubjectMarkProj> getMarksByName(@RequestParam (name="name")String name){
	return collegeService.getMarksByName(name);
}

}
