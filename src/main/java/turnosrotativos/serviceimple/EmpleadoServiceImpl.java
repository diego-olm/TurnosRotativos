package turnosrotativos.serviceimple;


import java.util.ArrayList;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import turnosrotativos.dtos.EmpleadoDTO;
import turnosrotativos.entities.Empleado;
import turnosrotativos.exceptions.BussinessException;
import turnosrotativos.interfaces.IOperacionEmpleado;
import turnosrotativos.repository.EmpleadoRepository;
import turnosrotativos.service.EmpleadoService;

@Service
public class EmpleadoServiceImpl implements EmpleadoService, IOperacionEmpleado {

	@Autowired
	private EmpleadoRepository repository;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public EmpleadoDTO agregarEmpleado(EmpleadoDTO empleado) {
		// TODO Auto-generated method stub
		validacionEmpleando(empleado);
		Empleado guardar = modelMapper.map(empleado, Empleado.class);
		guardar = repository.save(guardar);
		EmpleadoDTO empleadoCompleto = modelMapper.map(guardar, EmpleadoDTO.class);
		return empleadoCompleto;
	}

	@Override
	public List<EmpleadoDTO> obtenerEmpleados() {
		List<Empleado> listaEntity = repository.findAll();
		List<EmpleadoDTO> listaDTO = new ArrayList<>();
		listaEntity.stream().forEach(p -> {
			listaDTO.add(modelMapper.map(p, EmpleadoDTO.class));
		});
		return listaDTO;
	}

	@Override
	public EmpleadoDTO obtenerInformacionEmpleado(String idEmpleado) {
		validarId(idEmpleado);
		int id = Integer.parseInt(idEmpleado);
		Empleado empleadoEntity = repository.getById(id);

		return modelMapper.map(empleadoEntity, EmpleadoDTO.class);
	}

	@Override
	public void validacionEmpleando(EmpleadoDTO empleado) {
		// TODO Auto-generated method stub
		Empleado empleadoValidar=modelMapper.map(empleado, Empleado.class);
		if (repository.existsByEmail(empleadoValidar.getEmail())) {
			throw new BussinessException("ya existe un empleado con el email ingresado", HttpStatus.CONFLICT);
		}
		if (repository.existsBynroDocumento(empleadoValidar.getNroDocumento())) {
			throw new BussinessException("ya existe un empleado con el documento ingresado", HttpStatus.CONFLICT);
		}
		
		
	}

	@Override
	public boolean esNumerico(String idEmpleado) {
		// TODO Auto-generated method stub
		return idEmpleado.matches("[0-9]+");
	}

	@Override
	public EmpleadoDTO actualizarEmpleado(String idEmpleado, EmpleadoDTO empleado) {
		// TODO Auto-generated method stub
		validacionEmpleando(empleado);
		EmpleadoDTO empleadoObtenido= obtenerInformacionEmpleado(idEmpleado);
		Empleado actualizado = modelMapper.map(empleado, Empleado.class);
		actualizado.setId(empleadoObtenido.getId());
		
		actualizado.setFechaCreacion(actualizado.getFechaCreacion());
		
		Empleado emple=repository.save(actualizado);
		return modelMapper.map(emple, EmpleadoDTO.class);
	}

	@Override
	public void eliminarEmpleado(String idEmpleado) {
		// TODO Auto-generated method stub
		validarId(idEmpleado);
		int id = Integer.parseInt(idEmpleado);
		repository.deleteById(id);
		
	}

	@Override
	public void validarId(String idEmpleado) {
		// TODO Auto-generated method stub
		if (!esNumerico(idEmpleado)) {
			throw new BussinessException("No se encontró el empleado con Id:" + idEmpleado, HttpStatus.NOT_FOUND);
		}
		int id = Integer.parseInt(idEmpleado);
		if (!repository.existsById(id)) {
			throw new BussinessException("No se encontró el empleado con Id:" + idEmpleado, HttpStatus.NOT_FOUND);
		}
		
	}



}
