package turnosrotativos.controllers;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import turnosrotativos.dtos.EmpleadoDTO;
import turnosrotativos.service.EmpleadoService;

@RestController
@RequestMapping("/")
public class EmpleadoController {

	@Autowired
	private EmpleadoService service;
	
	
	@PostMapping("empleado")
	public ResponseEntity<EmpleadoDTO> CrearEmpleado(@Valid @RequestBody EmpleadoDTO empleado){
		
		
		return new ResponseEntity<>(service.agregarEmpleado(empleado), HttpStatus.CREATED);
	}
	@GetMapping("empleado")
	public ResponseEntity<List<EmpleadoDTO>> obtenerEmpleados(){
		
		
		return new ResponseEntity<>(service.obtenerEmpleados(), HttpStatus.OK);
	}
	@GetMapping("empleado/{empleadoId}")
	public ResponseEntity<EmpleadoDTO> obtenerInformacionEmpleado(@PathVariable("empleadoId") String id){
		
		return new ResponseEntity<>(service.obtenerInformacionEmpleado(id), HttpStatus.OK);
	}
	@PutMapping("empleado/{empleadoId}")
	public ResponseEntity<EmpleadoDTO> actualizarEmpleado(@Valid @PathVariable("empleadoId") String id, @RequestBody EmpleadoDTO empleado){
		
		return new ResponseEntity<>(null,HttpStatus.CREATED);
	}
	@DeleteMapping("empleado/{empleadoId}")
	public ResponseEntity<Void>eliminarEmpleado(@PathVariable("empleadoId") String idEmpleado){
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		
		
	}
}
