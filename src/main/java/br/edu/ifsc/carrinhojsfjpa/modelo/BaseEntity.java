package br.edu.ifsc.carrinhojsfjpa.modelo;

import java.io.Serializable;

public abstract class BaseEntity implements Serializable {

	private static final long serialVersionUID = 4004125033854459548L;

	public abstract Long getId();

	public abstract void setId(Long id);
}
