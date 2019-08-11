package com.ark.bowling.exceptions;

public class InvalidThrowException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public InvalidThrowException() {		
	}
	
	public InvalidThrowException(String msg){
		super(msg);
	} 
}
