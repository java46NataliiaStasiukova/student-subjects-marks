package telran.spring.data.entities;
import jakarta.persistence.*;

@Entity
@Table(name="marks")
public class MarkEntity {

@GeneratedValue(strategy = GenerationType.IDENTITY)
@Id
long id;

@ManyToOne//annotation for foreign key
@JoinColumn(name = "student_id")//column for connection
StudentEntity student;

@ManyToOne
@JoinColumn(name = "subject_id")
SubjectEntity subject;

int mark;

public MarkEntity(StudentEntity student, SubjectEntity subject, int mark) {
	super();
	this.student = student;
	this.subject = subject;
	this.mark = mark;
}

public MarkEntity() {
	
}

public long getId() {
	return id;
}

public StudentEntity getStudent() {
	return student;
}

public SubjectEntity getSubject() {
	return subject;
}

public int getMark() {
	return mark;
}

@Override
public String toString() {
	
	return String.format("ID: %s, stid: %s, suid: %s, mark: %s", 
			id, student.toString(), subject.toString(), mark);
	
}

}
