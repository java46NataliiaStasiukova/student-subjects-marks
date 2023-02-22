package telran.spring.data.service;

import java.util.List;

import telran.spring.data.model.*;
import telran.spring.data.proj.*;

public interface CollegeService {
	void addStudent(Student student);
	void addSubject(Subject subject);
	void addMark(Mark mark);
	List<MarkProj> getMarksByNameSubject(String name, String subject);
	List<StudentSubjectMark> getMarksByName(String name);
	
	List<StudentAvgMark> getStudentsAvgMark();
	List<StudentName> getBestStudents();
	List<StudentName> getTopBestStudents(int nStudents);
	List <StudentName> getTopBestStudentsSubject(int nStudents, String subject);
	List<StudentSubjectMark> getMarksOfWorstStudents(int nStudents);
	List<IntervalMarksCount> marksDistibution(int interval);
	
	//in real application should be with security (administrative part of application)
	List<String> getSqlQuery(String sqlQuery);
	List<String> getJpqlQuery(String jpqlQuery);
	List<String> removeStudents(double markCountLess);//removing all students having avg(mark)
	// * count(mark) less than a given value
	
	List<String> removeLeastPopularSubjects(int marksThreshold);
	
}

