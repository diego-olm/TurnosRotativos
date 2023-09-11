package turnosrotativos.interfaces;

import turnosrotativos.dtos.JornadaDTO;

public interface IOperacionesJornada {

	public void validadEmpleadoConcepto(JornadaDTO jornada);
	public void validacionNegocio(JornadaDTO jornada);
}
