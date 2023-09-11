package turnosrotativos.dtos;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
public class ConceptoDTO {

	private Integer id;
	private String nombre;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Integer hsMinimo;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Integer hsMaximo;
	private boolean laborable;
}
