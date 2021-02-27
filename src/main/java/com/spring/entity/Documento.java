package com.spring.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table (name="documento")
public class Documento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer iddocumento;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date fecha_registro;
	
	private String nombre_original;
	
	private byte[] link_original;
	
	private String nombre_firmado;
	
	private byte[] link_firmado;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(
			name = "users_documents",
			joinColumns = @JoinColumn(name = "document_id"),
			inverseJoinColumns = @JoinColumn(name = "user_id")
			)
	private Set<Usuario> users = new HashSet<>();
	
	private String estado;
	
	
	public Documento() {
	}

	public Documento(Integer iddocumento, Date fecha_registro, String nombre_original, byte[] link_original,
			String nombre_firmado, byte[] link_firmado, Set<Usuario> users, String estado) {
		this.iddocumento = iddocumento;
		this.fecha_registro = fecha_registro;
		this.nombre_original = nombre_original;
		this.link_original = link_original;
		this.nombre_firmado = nombre_firmado;
		this.link_firmado = link_firmado;
		this.users = users;
		this.estado = estado;
	}
	
	

	public Integer getIddocumento() {
		return iddocumento;
	}

	public void setIddocumento(Integer iddocumento) {
		this.iddocumento = iddocumento;
	}

	public Date getFecha_registro() {
		return fecha_registro;
	}

	public void setFecha_registro(Date fecha_registro) {
		this.fecha_registro = fecha_registro;
	}

	public String getNombre_original() {
		return nombre_original;
	}

	public void setNombre_original(String nombre_original) {
		this.nombre_original = nombre_original;
	}

	public byte[] getLink_original() {
		return link_original;
	}

	public void setLink_original(byte[] link_original) {
		this.link_original = link_original;
	}

	public String getNombre_firmado() {
		return nombre_firmado;
	}

	public void setNombre_firmado(String nombre_firmado) {
		this.nombre_firmado = nombre_firmado;
	}

	public byte[] getLink_firmado() {
		return link_firmado;
	}

	public void setLink_firmado(byte[] link_firmado) {
		this.link_firmado = link_firmado;
	}

	public Set<Usuario> getUsers() {
		return users;
	}

	public void setUsers(Set<Usuario> users) {
		this.users = users;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	
	
	
}
