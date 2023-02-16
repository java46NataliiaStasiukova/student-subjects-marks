package telran.spring.data.entities;
import jakarta.persistence.*;

@Entity
@Table(name="students")//not required
public class StudentEntity {
@Id
@Column(name="stid")
long id;
@Column(unique = true)
String name;

public StudentEntity(long id, String name) {
	super();
	this.id = id;
	this.name = name;
}

public StudentEntity() {
	
}

@Override
public String toString() {
	
	return String.format("id: %s, name: %s", id, name);
	
}

}
