package com.okiimport.web_service.modelo;

//import com.okiimport.app.modelo.Moneda;

import java.io.Serializable;
import java.lang.Float;
import java.lang.Integer;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * Entity implementation class for Entity: HistoricoMoneda
 *
 */
@Entity
@Table(name="historico_moneda")
@NamedQuery(name="HistoricoMoneda.findAll", query="SELECT h FROM HistoricoMoneda h")
@JsonIgnoreProperties({"cotizacions"})
public class HistoricoMoneda implements Serializable {
	private static final long serialVersionUID = 1L;
	   
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="historico_moneda_id_seq")
	@SequenceGenerator(name="historico_moneda_id_seq", sequenceName="historico_moneda_id_seq", initialValue=1, allocationSize=1)
	@Column(name="id_historia", nullable=false, unique=true)
	private Integer idHistoria;
	
	@Column(name="monto_conversion")
	private Float montoConversion;
	
	@Column(name="fecha_creacion")
	private Date fechaCreacion;
	
	//bi-directional many-to-one association to Moneda
	@ManyToOne
	@JoinColumn(name="id_moneda")
	private Moneda moneda;
	
	//bi-directional one-to-many association to Cotizacion
	@OneToMany(mappedBy="historicoMoneda")
	private List<Cotizacion> cotizacions;

	public HistoricoMoneda() {
		this.moneda = new Moneda();
	}   
	public Integer getIdHistoria() {
		return this.idHistoria;
	}

	public void setIdHistoria(Integer idHistoria) {
		this.idHistoria = idHistoria;
	}   
	public Moneda getMoneda() {
		return this.moneda;
	}

	public void setMoneda(Moneda moneda) {
		this.moneda = moneda;
	}   
	public Float getMontoConversion() {
		return this.montoConversion;
	}

	public void setMontoConversion(Float montoConversion) {
		this.montoConversion = montoConversion;
	}   
	public Date getFechaCreacion() {
		return this.fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	
	public List<Cotizacion> getCotizacions() {
		return cotizacions;
	}
	
	public void setCotizacions(List<Cotizacion> cotizacions) {
		this.cotizacions = cotizacions;
	}
}
