package turnosrotativos.interfaces;

import turnosrotativos.dtos.EmpleadoDTO;

public interface IOperacionEmpleado {

	public void validacionEmpleando(EmpleadoDTO empleado);
	public boolean esNumerico(String idEmpleado);
	public void validarId(String idEmpleado);
}
