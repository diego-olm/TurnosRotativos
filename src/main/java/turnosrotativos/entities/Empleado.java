package turnosrotativos.entities;



import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Entity(name = "empleados")
public class Empleado {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@NotNull(message = "nroDocumento es obligatorio")
	
	@Column(name = "nro_documento", nullable = false)
	private Number nroDocumento;
	
	@NotNull(message = "nombre es obligatorio")
	@NotBlank(message = "nombre es obligatorio")
	@Column(length = 30)
	private String nombre;
	
	@NotNull(message = "apellido es obligatorio")
	@NotBlank(message = "apellido es obligatorio")
	@Column(length = 30)
	private String apellido;
	
	@Column(name="email", nullable=false, length = 50)
	@NotBlank(message="email es obligatorio")
	@Email(message="El email ingresado no es correcto")
	private String email;
	
	@NotNull(message = "fechaNacimiento es obligatorio")
	@Column(name = "fecha_nacimiento")
	private LocalDate fechaNacimiento;
	
	@NotNull(message = "fechaNacimiento es obligatorio")
	@Column(name = "fecha_ingreso", nullable = false)
	private LocalDate fechaIngreso;
	
	@Column(name = "fecha_creacion", nullable = false)

	@CreationTimestamp
	private Date fechaCreacion;


}
