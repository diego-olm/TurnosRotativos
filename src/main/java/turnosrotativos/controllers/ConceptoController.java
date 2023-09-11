package turnosrotativos.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import turnosrotativos.dtos.ConceptoDTO;
import turnosrotativos.service.ConceptoService;

@RestController
@RequestMapping("/")
public class ConceptoController {

	@Autowired
	private ConceptoService service;
	@GetMapping("concepto")
	public ResponseEntity<List<ConceptoDTO>> obtenerConcepto(){
		
		return new ResponseEntity<>(service.obtenerConcepto(),HttpStatus.OK);
	}
}
