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
	List<StudentSubjectMark> findByStudentName(String name);
	
	//TODO
	@Query(value = "select name, round(avg(mark)) as AvarageMark from students st\n"
			+ "join marks m on m.stid=st.stid\n"
			+ "group by st.name", nativeQuery = true)
	List<StudentAvgMark> findStudentsByAverageMark();
	
	@Query(value = "select name from students st "
			+ "join marks m on m.stid=st.stid "
			+ "group by st.name having avg(mark) > ( "
			+ "select avg(mark) from marks)", nativeQuery = true)
	List<StudentName> findStudentsByMaxAverageMark();
	
	@Query(value = "select name from students st\n"
			+ "join marks m on m.stid=st.stid\n"
			+ "group by st.name order by round(avg(mark)) desc limit(:nStudents)", nativeQuery = true)
	List<StudentName> findStudentsByMaxAverageMark(int nStudents);

	@Query(value = "select name from students st "
			+ "join marks m on m.stid=st.stid join subjects sb on m.suid=sb.suid "
			+ "where sb.subject =:subject order by mark desc limit(:nStudents)", nativeQuery = true)
	List<StudentName> findStudentsByMaxAverageMark(int nStudents, String subject);
	
	@Query(value = "select name, subject, mark from students st "
			+ "join marks m on m.stid=st.stid "
			+ "join subjects sb on m.suid=sb.suid "
			+ "where st.name in ( "
			+ "select name from students st "
			+ "join marks m on m.stid=st.stid "
			+ "group by st.name order by round(avg(mark)) limit(:nStudents) "
			+ ")", nativeQuery = true)
	List<StudentSubjectMark> findStudentsByMinAverageMark(int nStudents);
	
	//TODO
	@Query(value = "select name, max(mark), min(mark), count(mark) from marks m "
			+ "join students st on st.stid=m.stid group by st.name", nativeQuery = true)
	List<IntervalMarksCount> findByStudentsMaxMarkAndMinMarkAndCountMarks(int interval);
	
}
