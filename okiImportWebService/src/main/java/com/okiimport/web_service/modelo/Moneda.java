package com.okiimport.web_service.modelo;

import java.io.Serializable;
import java.lang.Boolean;
import java.lang.Integer;
import java.lang.String;
import java.util.List;

import javax.persistence.*;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * Entity implementation class for Entity: Moneda
 *
 */
@Entity
@Table(name="moneda")
@NamedQuery(name="Moneda.findAll", query="SELECT m FROM Moneda m")
@JsonIgnoreProperties({"historicoMonedas"})
public class Moneda implements Serializable {
	private static final long serialVersionUID = 1L;
	   
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="moneda_id_seq")
	@SequenceGenerator(name="moneda_id_seq", sequenceName="moneda_id_seq", initialValue=1, allocationSize=1)
	@Column(name="id_moneda", nullable=false, unique=true)
	private Integer idMoneda;
	
	private String nombre;
	
	private Boolean pais;
	
	private String simbolo;
	
	private String estatus;
	
	//bi-directional one-to-many association to HistoricoMoneda
	@OneToMany(mappedBy="moneda", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<HistoricoMoneda> historicoMonedas;

	public Moneda() {
		super();
	}   
	public Integer getIdMoneda() {
		return this.idMoneda;
	}

	public void setIdMoneda(Integer idMoneda) {
		this.idMoneda = idMoneda;
	}   
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}   
	public Boolean getPais() {
		return this.pais;
	}

	public void setPais(Boolean pais) {
		this.pais = pais;
	}   
	public String getSimbolo() {
		return this.simbolo;
	}

	public void setSimbolo(String simbolo) {
		this.simbolo = simbolo;
	}
	
	public String getEstatus() {
		return estatus;
	}
	
	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}
	
	public List<HistoricoMoneda> getHistoricoMonedas() {
		return historicoMonedas;
	}
	
	public void setHistoricoMonedas(List<HistoricoMoneda> historicoMonedas) {
		this.historicoMonedas = historicoMonedas;
	}
	
}
