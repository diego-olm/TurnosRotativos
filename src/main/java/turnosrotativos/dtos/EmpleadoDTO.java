package turnosrotativos.dtos;

import java.sql.Date;
import java.time.LocalDate;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import turnosrotativos.validacionpersonalizada.EsMayor;

@Getter
@Setter

@Component
public class EmpleadoDTO {

	private Integer id;

	@NotNull(message = "nroDocumento es obligatorio")
	private Number nroDocumento;

	@NotNull(message = "nombre es obligatorio")
	@NotBlank(message = "nombre es obligatorio")
	@Pattern(regexp = "^[a-zA-Z]+$", message = "Solo se permiten letras en el campo 'nombre'")
	private String nombre;

	@NotNull(message = "apellido es obligatorio")
	@NotBlank(message = "apellido es obligatorio")
	@Pattern(regexp = "^[a-zA-Z]+$", message = "Solo se permiten letras en el campo 'apellido'")
	private String apellido;

	@NotNull(message = "email es obligatorio")
	@NotBlank(message = "email es obligatorio")
	@Email(message = "el email ingresado no es correcto")
	private String email;

	@NotNull(message = "fechaNacimiento es obligatorio")
	@Past(message = "la fecha de nacimiento no puede ser posterior al dia de la fecha")
	@EsMayor(message = "La edad del empleado no puede ser menor a 18 años.")
	private LocalDate fechaNacimiento;

	@NotNull(message = "fechaIngreso es obligatorio")
	@Past(message = "la fecha de ingreso no puede ser posterior al dia de la fecha")
	private LocalDate fechaIngreso;

	private Date fechaCreacion;
	
}
