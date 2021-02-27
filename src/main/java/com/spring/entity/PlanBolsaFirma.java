package com.spring.entity;


import javax.persistence.*;

@Entity
@Table(name = "bolsafirma")
public class PlanBolsaFirma{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idbolsafirma;
	private String planbolsa;
	private double precio;
	private int cantidadfirmas;

	
	
	public PlanBolsaFirma() {
		
	}




	public Integer getIdbolsafirma() {
		return idbolsafirma;
	}




	public void setIdbolsafirma(Integer idbolsafirma) {
		this.idbolsafirma = idbolsafirma;
	}



	public String getPlanbolsa() {
		return planbolsa;
	}





	public void setPlanbolsa(String planbolsa) {
		this.planbolsa = planbolsa;
	}





	public double getPrecio() {
		return precio;
	}



	public void setPrecio(double precio) {
		this.precio = precio;
	}





	public int getCantidadfirmas() {
		return cantidadfirmas;
	}





	public void setCantidadfirmas(int cantidadfirmas) {
		this.cantidadfirmas = cantidadfirmas;
	}



	
	
}
