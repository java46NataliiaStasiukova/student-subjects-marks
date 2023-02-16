package telran.spring.data.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import telran.spring.data.entities.*;
import telran.spring.data.proj.*;

public interface MarkRepository extends JpaRepository<MarkEntity, Long> {
	
	List<MarkProj> findByStudentNameAndSubjectSubject(String name, String subject);
	@Query(value = "select name, subject, mark "
			+ "from students student join marks on student.stid=marks.stid "
			+ "join subjects subject on subject.suid=marks.suid "
			+ "where student.name=:name", nativeQuery = true)
	List<StudentSubjectMarkProj> findByStudentName(String name);

}
