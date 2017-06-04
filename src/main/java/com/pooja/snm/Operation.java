package com.pooja.snm;

public enum Operation {
	
	ADD("Add"),
	
	SUBTRACT("Subtract"),
	
	MULTIPLY("Multiply");
	
	
	private final String operation;
	
	private Operation( final String nameValue){

		operation = nameValue;
	}
	

    public String getOperation() {
        return operation;
    }

}
