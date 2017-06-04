package com.pooja.snm.model;

public class Sale {
	
	private final Product productType;

	private long productValue;

	private int quantity;

	public Sale(Product productType, long productValue, int quantity) {
		this.productType = productType;
		this.productValue = productValue;
		this.quantity = quantity;
	}

	public Product getProductType() {
		return productType;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public long getProductValue() {
		return productValue;
	}

	public void setProductValue(long productValue) {
		this.productValue = productValue;
	}

	@Override
	public String toString() {
		return "Sale [productType=" + productType + ", productValue=" + productValue + ", quantity=" + quantity + "]";
	}

  	
	

}
