package ec.edu.luisrogerio.persistence.empleador;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ec.edu.luisrogerio.domain.empleador.DatosEmpleador;

@Repository
public interface DatosEmpleadorRepository extends JpaRepository<DatosEmpleador, Long> {

	Optional<DatosEmpleador> findByRuc(String ruc);

}