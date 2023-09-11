package turnosrotativos.serviceimple;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import turnosrotativos.dtos.ConceptoDTO;
import turnosrotativos.entities.Concepto;
import turnosrotativos.repository.ConceptoRepository;
import turnosrotativos.service.ConceptoService;

@Service
public class ConceptoServiceImpl implements ConceptoService{

	@Autowired
	private ConceptoRepository repository;
	@Autowired
	private ModelMapper modelMapper;
	@Override
	public List<ConceptoDTO> obtenerConcepto() {
		// TODO Auto-generated method stub
		List<Concepto> listaConcepto= new ArrayList<>();
		listaConcepto= repository.findAll();
		List<ConceptoDTO> listaDTO = new ArrayList<>();
		listaConcepto.stream().forEach(p -> {
			listaDTO.add(modelMapper.map(p, ConceptoDTO.class));
		});
		return listaDTO;
	}

}
