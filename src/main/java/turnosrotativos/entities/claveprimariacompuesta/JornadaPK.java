package turnosrotativos.entities.claveprimariacompuesta;

import java.io.Serializable;
import java.time.LocalDate;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@EqualsAndHashCode
public class JornadaPK implements Serializable {

	private Integer idEmpleado;
	private Integer idConcepto;
	private LocalDate fecha;
}
