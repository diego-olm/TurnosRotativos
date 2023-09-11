package turnosrotativos.service;


import java.util.List;

import org.springframework.stereotype.Service;

import turnosrotativos.dtos.EmpleadoDTO;

@Service
public interface EmpleadoService {

	public EmpleadoDTO agregarEmpleado(EmpleadoDTO empleado);
	public List<EmpleadoDTO> obtenerEmpleados();
	public EmpleadoDTO obtenerInformacionEmpleado(String idEmpleado);
	public EmpleadoDTO actualizarEmpleado(String idEmpleado, EmpleadoDTO empleado);
	public void eliminarEmpleado(String idEmpleado);
}
