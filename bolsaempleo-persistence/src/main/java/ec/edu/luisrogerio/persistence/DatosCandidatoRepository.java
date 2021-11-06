package ec.edu.luisrogerio.persistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ec.edu.luisrogerio.domain.DatosCandidato;

@Repository
public interface DatosCandidatoRepository extends JpaRepository<DatosCandidato, Long> {
	
	Optional<DatosCandidato> findByCedula(String cedula);
}
