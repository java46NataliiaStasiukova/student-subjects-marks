package telran.spring.data.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import telran.spring.data.entities.*;

public interface SubjectRepository extends JpaRepository<SubjectEntity, Long> {

//JPQL
    @Query("select subjects from SubjectEntity subjects where id in"
    		+ " (select subject.id from MarkEntity"
    		+ " group by subject.id having count(mark) < :markCountLess)")
	List<SubjectEntity> worstSubject(int markCountLess);

}
