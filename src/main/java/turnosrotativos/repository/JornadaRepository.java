package turnosrotativos.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import turnosrotativos.entities.Jornada;
import turnosrotativos.entities.claveprimariacompuesta.JornadaPK;

public interface JornadaRepository extends JpaRepository<Jornada, JornadaPK>{

	boolean existsByIdEmpleadoAndIdConceptoAndFecha(Integer idEmpleado,Integer idConcepto,LocalDate fecha);
    boolean existsByIdEmpleadoAndAndFecha(Integer idEmpleado, LocalDate fecha);

    @Query("SELECT j FROM Jornada j WHERE j.idEmpleado = :idEmpleado " +
    	       
    	       "AND j.fecha BETWEEN :inicioSemana AND :finSemana")
    List<Jornada> jornadaSemanaEmpleado(Integer idEmpleado,
            LocalDate inicioSemana,
            LocalDate finSemana);
}
