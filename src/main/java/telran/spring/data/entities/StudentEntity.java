package telran.spring.data.entities;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name="students")//not required
public class StudentEntity {
@Id
@Column(name="stid")
long id;
@Column(unique = true)
String name;
@OneToMany(mappedBy = "student", cascade = CascadeType.REMOVE)
List<MarkEntity> marks;
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

public long getId() {
	return id;
}

public String getName() {
	return name;
}

}
