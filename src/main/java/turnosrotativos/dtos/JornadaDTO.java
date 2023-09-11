package turnosrotativos.dtos;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
public class JornadaDTO {

	@NotNull(message = "idEmpleado es obligatorio")
	@Column(name = "id_empleado")
	private Integer idEmpleado;
	@NotNull(message = "idConcepto es obligatorio")
	@Column(name = "id_concepto")
	private Integer idConcepto;
	@NotNull(message = "fecha es obligatorio")
	private LocalDate fecha;
	@Column(name = "horas_trabajadas")
	private Integer horasTrabajadas;
}
