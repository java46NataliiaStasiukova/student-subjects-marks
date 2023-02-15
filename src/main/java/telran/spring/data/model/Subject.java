package telran.spring.data.model;

import java.util.Objects;

public class Subject {

public long id;
public String name;

public Subject(long id, String name) {
	super();
	this.id = id;
	this.name = name;
}

public Subject() {
	
}

@Override
public int hashCode() {
	return Objects.hash(id, name);
}

@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Subject other = (Subject) obj;
	return id == other.id && Objects.equals(name, other.name);
}

}
