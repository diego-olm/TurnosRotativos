package turnosrotativos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import turnosrotativos.entities.Empleado;

public interface EmpleadoRepository extends JpaRepository<Empleado, Integer> {

	boolean existsByEmail(String email);
	boolean existsBynroDocumento(Number nroDocumento);
	Empleado getById(int id);
}
