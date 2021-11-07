package ec.edu.luisrogerio.service.admin;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.edu.luisrogerio.domain.admin.DatosAdmin;
import ec.edu.luisrogerio.persistence.admin.DatosAdminRepository;
import ec.edu.luisrogerio.service.GenericCRUDServiceImpl;

@Service
public class DatosAdminService extends GenericCRUDServiceImpl<DatosAdmin, Long> {

	@Autowired
	private DatosAdminRepository entityRepository;

	@Override
	public Optional<DatosAdmin> buscar(DatosAdmin entity) {
		return entityRepository.findById(entity.getId());
	}

	public Optional<DatosAdmin> buscarPorCedula(String cedula) {
		return entityRepository.findByCedula(cedula);
	}

}