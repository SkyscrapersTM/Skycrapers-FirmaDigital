package com.spring.entity;


import javax.persistence.*;

@Entity
@Table(name = "empresa")
public class Empresa{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idempresa;
	private long ruc;
	private String razon_social;
	private String giro_negocio;
	private String direccion;
	private String referencia;
	private String correo;
	private int telefono;
	private int saldofirmas;

	
	
	public Empresa() {
		
	}

	public Integer getIdempresa() {
		return idempresa;
	}
	public void setIdempresa(Integer idempresa) {
		this.idempresa = idempresa;
	}
	public long getRuc() {
		return ruc;
	}
	public void setRuc(long ruc) {
		this.ruc = ruc;
	}
	public String getRazon_social() {
		return razon_social;
	}
	public void setRazon_social(String razon_social) {
		this.razon_social = razon_social;
	}
	public String getGiro_negocio() {
		return giro_negocio;
	}
	public void setGiro_negocio(String giro_negocio) {
		this.giro_negocio = giro_negocio;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public int getTelefono() {
		return telefono;
	}
	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	public int getSaldofirmas() {
		return saldofirmas;
	}

	public void setSaldofirmas(int saldofirmas) {
		this.saldofirmas = saldofirmas;
	}

}
