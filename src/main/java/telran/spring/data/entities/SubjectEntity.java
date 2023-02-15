package telran.spring.data.entities;
import jakarta.persistence.*;

@Entity
@Table(name="subjects")
public class SubjectEntity {
@Id
long id;
@Column(unique = true)
String subject;

public SubjectEntity(long id, String subject) {
	super();
	this.id = id;
	this.subject = subject;
}

public SubjectEntity() {
		
}

@Override
public String toString() {
	
	return String.format("id: %s, subject: %s", id, subject);
	
}

}
