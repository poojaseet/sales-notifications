package com.pooja.snm.model;

import com.pooja.snm.Operation;

public class Adjustment {

	private final long value;

	private final Operation op;
	
	private final Product product;


	public Adjustment(long value, Operation op, Product product) {
		this.value = value;
		this.op = op;
		this.product = product;
	}

	public long getValue() {
		return value;
	}

	public Operation getOp() {
		return op;
	}



}
