package com.ark.bowling.exceptions;

public class InvalidFrameException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public InvalidFrameException() {}
	
	public InvalidFrameException(String msg){
		super(msg);
	} 
}
