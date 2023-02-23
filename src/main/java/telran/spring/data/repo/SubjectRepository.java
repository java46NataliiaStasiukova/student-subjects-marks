package telran.spring.data.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import telran.spring.data.entities.*;

public interface SubjectRepository extends JpaRepository<SubjectEntity, Long> {

//JPQL
//    @Query("select course from SubjectEntity course where id in"
//    		+ " (select subject.id from MarkEntity"
//    		+ " group by subject.id having count(mark) < :markCountLess)")
	
//JPQL - Solution
	@Query("select course from SubjectEntity course where subject in "
			+ "(select ms.subject from MarkEntity me right "
			+ "join me.subject ms group by ms.subject "
			+ "having count(mark) < :markThreshold)")
	
	List<SubjectEntity> worstSubject(int markThreshold);

}
