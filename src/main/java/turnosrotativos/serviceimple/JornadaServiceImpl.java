package turnosrotativos.serviceimple;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import turnosrotativos.dtos.JornadaDTO;
import turnosrotativos.entities.Jornada;
import turnosrotativos.exceptions.BussinessException;
import turnosrotativos.interfaces.IOperacionesJornada;
import turnosrotativos.repository.ConceptoRepository;
import turnosrotativos.repository.EmpleadoRepository;
import turnosrotativos.repository.JornadaRepository;
import turnosrotativos.service.JornadaService;

@Service
public class JornadaServiceImpl implements JornadaService, IOperacionesJornada {

	@Autowired
	private JornadaRepository jornadaRepository;
	@Autowired
	private EmpleadoRepository empleadoRepository;
	@Autowired
	private ConceptoRepository conceptoRepository;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public JornadaDTO agregarJornada(JornadaDTO jornada) {
		validadEmpleadoConcepto(jornada);
		validacionNegocio(jornada);
		Jornada jornadaEntity = modelMapper.map(jornada, Jornada.class);
		jornadaEntity = jornadaRepository.save(jornadaEntity);
		
		return modelMapper.map(jornadaEntity, JornadaDTO.class);
	}

	@Override
	public void validadEmpleadoConcepto(JornadaDTO jornada) {
		Jornada jornadaEntity = modelMapper.map(jornada, Jornada.class);

		if (!empleadoRepository.existsById(jornadaEntity.getIdEmpleado())) {
			throw new BussinessException("No existe el empleado ingresado", HttpStatus.NOT_FOUND);
		}
		if (!conceptoRepository.existsById(jornadaEntity.getIdConcepto())) {
			throw new BussinessException("No existe el concepto ingresado", HttpStatus.NOT_FOUND);
		}
		String turnoNormal = "Turno Normal";
		String turnoExtra = "Turno Extra";
		String diaLibre="Dia Libre";
		Integer horas= jornadaEntity.getHorasTrabajadas();
		String nombreConcepto = conceptoRepository.getReferenceById(jornadaEntity.getIdConcepto()).getNombre();
		if ((nombreConcepto.equals(turnoNormal) && horas <= 0)
				|| (nombreConcepto.equals(turnoExtra) && horas<= 0)) {
			throw new BussinessException("hsTrabajadas es obligatorio para el concepto ingresado.", HttpStatus.BAD_REQUEST);
		}
		if(nombreConcepto.equals(diaLibre) && horas>0 ) {
			throw new BussinessException("El concepto ingresado no requiere el ingreso de hsTrabajadas.", HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public void validacionNegocio(JornadaDTO jornada) {
		// TODO Auto-generated method stub
		Integer hsMaximo=0;
		Integer hsMinimo=0;
		if(conceptoRepository.getReferenceById(jornada.getIdConcepto()).getHsMaximo()!=null
		   && conceptoRepository.getReferenceById(jornada.getIdConcepto()).getHsMinimo()!=null) {
			hsMaximo= conceptoRepository.getReferenceById(jornada.getIdConcepto()).getHsMaximo();
			hsMinimo= conceptoRepository.getReferenceById(jornada.getIdConcepto()).getHsMinimo();
		}

		
		if(jornada.getHorasTrabajadas()<hsMinimo || jornada.getHorasTrabajadas()>hsMaximo ) {
			throw new BussinessException("El rango de horas que se puede cargar para este concepto es de " 
		+ hsMinimo + " - "+ hsMaximo, HttpStatus.BAD_REQUEST);
		}
		if(jornadaRepository.existsByIdEmpleadoAndIdConceptoAndFecha(jornada.getIdEmpleado(),3, jornada.getFecha())) {
			throw new BussinessException("El empleado ingresado cuenta con un día libre en esa fecha.", HttpStatus.BAD_REQUEST);
		}
		if(jornadaRepository.existsByIdEmpleadoAndIdConceptoAndFecha(jornada.getIdEmpleado(),jornada.getIdConcepto(), jornada.getFecha())) {
			throw new BussinessException("El empleado ya tiene registrado una jornada con este concepto en la fecha ingresada.", HttpStatus.BAD_REQUEST);
		}
		
		if(jornada.getIdConcepto()==3) {
		if(jornadaRepository.existsByIdEmpleadoAndIdConceptoAndFecha(jornada.getIdEmpleado(),1, jornada.getFecha())
					|| jornadaRepository.existsByIdEmpleadoAndIdConceptoAndFecha(jornada.getIdEmpleado(),2, jornada.getFecha())) {
				throw new BussinessException("El empleado no puede cargar Dia Libre si cargo un turno previamente para la fecha ingresada.",HttpStatus.BAD_REQUEST);
				}
		}
		LocalDate fechaInicioSemana= obtenerInicio(jornada.getFecha());
		LocalDate fechaFinSemana=obtenerFinDesemana(jornada.getFecha());
		List<Jornada> listaJornada=jornadaRepository.jornadaSemanaEmpleado(jornada.getIdEmpleado(),fechaInicioSemana,fechaFinSemana);
		
		if(listaJornada.stream().mapToInt(Jornada:: getHorasTrabajadas).sum()+jornada.getHorasTrabajadas()>48) {
			throw new BussinessException("El empleado ingresado supera las 48 horas semanales.",HttpStatus.BAD_REQUEST );
		}
		if(listaJornada.stream().filter(j-> j.getIdConcepto()==2).count()>=3) {
			throw new BussinessException("El empleado ingresado ya cuenta con 3 turnos extra esta semana",HttpStatus.BAD_REQUEST );
		}
		if(listaJornada.stream().filter(j-> j.getIdConcepto()==1).count()>=5) {
			throw new BussinessException("El empleado ingresado ya cuenta con 5 turnos normales esta semana.",HttpStatus.BAD_REQUEST );
		}
		if(listaJornada.stream().filter(j-> j.getIdConcepto()==3).count()>=2) {
			throw new BussinessException("El empleado no cuenta con más días libres esta semana",HttpStatus.BAD_REQUEST );
		}
	}
	public LocalDate obtenerInicio(LocalDate fecha) {
		
		LocalDate fechaInicioSemana = fecha.with(DayOfWeek.MONDAY);
		return fechaInicioSemana;
	}
public LocalDate obtenerFinDesemana(LocalDate fecha) {
		
	LocalDate fechaFinSemana = fecha.with(DayOfWeek.SUNDAY);
		return fechaFinSemana;
	}
}
