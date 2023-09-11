package turnosrotativos.validacionpersonalizada;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = EsMayorValidacion.class )
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface EsMayor {
	
	public String message() default "La edad del empleado no puede ser menor a 18 años.";
	Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
