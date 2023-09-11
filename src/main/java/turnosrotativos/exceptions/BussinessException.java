package turnosrotativos.exceptions;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class BussinessException extends RuntimeException {

	private  HttpStatus status;
	
	public BussinessException(String message) {
		super(message);
	}
	public BussinessException(String message, HttpStatus status) {
		super(message);
		this.status=status;}

	
}
