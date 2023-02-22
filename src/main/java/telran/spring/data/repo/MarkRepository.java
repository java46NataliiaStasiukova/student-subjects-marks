package telran.spring.data.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import telran.spring.data.entities.*;
import telran.spring.data.proj.*;

public interface MarkRepository extends JpaRepository<MarkEntity, Long> {
	
	static final String MIN_INTERVAL = "floor(mark/:interval) * :interval";

	
	List<MarkProj> findByStudentNameAndSubjectSubject(String name, String subject);
//SQL
//	@Query(value = "select name, subject, mark "
//			+ "from students student join marks on student.stid=marks.stid "
//			+ "join subjects subject on subject.suid=marks.suid "
//			+ "where student.name=:name", nativeQuery = true)
//JPQL
	@Query("select student.name as name, subject.subject as subject, mark as mark "
			+ "from MarkEntity where student.name = :name")
	List<StudentSubjectMark> findByStudentName(String name);
	
//SQL
//	@Query(value = "select name, round(avg(mark)) as AvgMark from students st "
//			+ "join marks m on m.stid=st.stid "
//			+ "group by st.name", nativeQuery = true)
//JPQL
	@Query("select student.name as name, avg(cast(mark as double)) "
			+ "as avgMark from MarkEntity group by student.name")
	List<StudentAvgMark> findStudentsByAverageMark();

//SQL
//	@Query(value = "select name from students st "
//			+ "join marks m on m.stid=st.stid "
//			+ "group by st.name having avg(mark) > ( "
//			+ "select avg(mark) from marks)", nativeQuery = true)
//JPQL	
	@Query("select student.name as name from MarkEntity group by student.name having avg(mark) > "
			+ "(select avg(mark) from MarkEntity)")
	List<StudentName> findStudentsByMaxAverageMark();

//SQL
//	@Query(value = "select name from students st "
//			+ "join marks m on m.stid=st.stid "
//			+ "group by st.name order by round(avg(mark)) desc limit(:nStudents)", nativeQuery = true)
//JPQL
	@Query("select student.name as name from MarkEntity group by student.name "
			+ "order by avg(mark) desc limit :nStudents")
	List<StudentName> findStudentsByMaxAverageMark(int nStudents);

//SQL
//	@Query(value = "select name from students st "
//			+ "join marks m on m.stid=st.stid join subjects sb on m.suid=sb.suid "
//			+ "where sb.subject =:subject order by mark desc limit(:nStudents)", nativeQuery = true)
//JPQL
	@Query("select student.name as name from MarkEntity where subject.subject = :subject "
			+ "order by mark desc limit :nStudents")
	List<StudentName> findStudentByMaxMarkBySubject(int nStudents, String subject);
	
//SQL
//	@Query(value = "select name, subject, mark from students st "
//			+ "join marks m on m.stid=st.stid "
//			+ "join subjects sb on m.suid=sb.suid "
//			+ "where st.name in ( "
//			+ "select name from students st "
//			+ "join marks m on m.stid=st.stid "
//			+ "group by st.name order by round(avg(mark))) limit(:nStudents)"
//			, nativeQuery = true)
//JPQL
	@Query("select student.name as name, subject.subject as subject, mark as mark from MarkEntity "
			+ "where student.name in  (select student.name from MarkEntity "
			+ "group by student.name order by avg(mark) limit :nStudents)")
	List<StudentSubjectMark> findStudentsByMinAverageMark(int nStudents);
	
//SQL	
//	@Query(value = "select floor(mark/:interval) * :interval as min,\n"
//			+ "floor(mark/:interval) * :interval + :interval - 1 as max,\n"
//			+ "count(*) as count from marks group by min, max order by min", nativeQuery = true)
//JPQL
	@Query("select " + MIN_INTERVAL +" as min,"
			+  MIN_INTERVAL + " + :interval - 1 as max, "
			+ "count(*) as count from MarkEntity group by min, max order by min")
	List<IntervalMarksCount> findByStudentsMaxMarkAndMinMarkAndCountMarks(int interval);
	
}
