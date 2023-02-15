package telran.spring.data.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import telran.spring.data.entities.*;

public interface StudentRepository extends JpaRepository<StudentEntity, Long> {

}
