package telran.spring.data.entities;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name="subjects")
public class SubjectEntity {
@Id
@Column(name="suid")
long id;
@Column(unique = true)
String subject;
@OneToMany(mappedBy = "subject", cascade = CascadeType.REMOVE)
List<MarkEntity> marks;
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

public long getId() {
	return id;
}

public String getSubject() {
	return subject;
}

}
