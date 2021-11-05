package ec.edu.luisrogerio.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ec.edu.luisrogerio.domain.DatosEmpleador;


@Repository
public interface DatosEmpleadorRepository extends JpaRepository<DatosEmpleador, Long> {

}