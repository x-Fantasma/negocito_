package co.negocito.exceptions.impl;

import co.negocito.exceptions.config.MalformedHeaderException;

public class ProductoExceptions extends MalformedHeaderException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ProductoExceptions(String detail) {
		super(detail);
	}
}
