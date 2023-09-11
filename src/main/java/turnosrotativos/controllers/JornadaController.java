package turnosrotativos.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import turnosrotativos.dtos.JornadaDTO;
import turnosrotativos.service.JornadaService;

@RestController("/")
public class JornadaController {

	@Autowired
	private JornadaService service;
	@PostMapping("jornada")
	public ResponseEntity<JornadaDTO> crearJornada(@Valid @RequestBody JornadaDTO jornada){
		
		
		return new ResponseEntity<>(service.agregarJornada(jornada),HttpStatus.CREATED);
	}
}
