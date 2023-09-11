package turnosrotativos.entities;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import turnosrotativos.entities.claveprimariacompuesta.JornadaPK;

@Entity
@Table(name = "jornada")
@IdClass(JornadaPK.class)
@Getter
@Setter

@ToString
public class Jornada implements Serializable{
	@Id
	
	@Column(name = "empleado_id")
	private Integer idEmpleado;
	@Id
	@Column(name = "concepto_id")
	private Integer idConcepto;
	
    @Id
    @Column(name = "fecha")
    private LocalDate fecha;
    @Column(name ="horas_trabajadas")
    private Integer horasTrabajadas;
}
