package com.capgem.pavani.exception;

public class ResourceNotFoundException  extends RuntimeException {
	 
	public ResourceNotFoundException()
	{
		super("Book with the given Id not on the server...please enter an existing id");
	}
	public ResourceNotFoundException(String message)
	{
		super(message);
	}
}
