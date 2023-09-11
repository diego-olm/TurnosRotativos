package turnosrotativos.validacionpersonalizada;

import java.time.LocalDate;
import java.time.Period;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EsMayorValidacion implements ConstraintValidator<EsMayor, LocalDate> {

	@Override
	public void initialize(EsMayor edad) {
	}

	@Override
	public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub

		if (value == null) {
			return false;
		}

		LocalDate fechaActual = LocalDate.now();
		Period periodo = Period.between(value, fechaActual);
		int edad = periodo.getYears();
		return edad >= 18;
	}

}
