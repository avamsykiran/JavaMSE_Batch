package com.cts.sksc.models;

public class ProductDetails {

	private Long productId;
	private String name;
	private Double quantity;
	
	public ProductDetails() {}
	
	public ProductDetails(Long productId, String name, Double quantity) {
		super();
		this.productId = productId;
		this.name = name;
		this.quantity = quantity;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "ProductDetails [productId=" + productId + ", name=" + name + ", quantity=" + quantity + "]";
	}
	
	
}
