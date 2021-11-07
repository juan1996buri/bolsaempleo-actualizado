package ec.edu.luisrogerio.persistence.candidato;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ec.edu.luisrogerio.domain.User;
import ec.edu.luisrogerio.domain.candidato.Experiencia;


@Repository
public interface ExperienciaRepository extends JpaRepository<Experiencia, Long> {
	
	List<Experiencia> findByUser(User user);
}