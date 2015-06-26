package com.okiimport.web_service.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="cotizacion")
@NamedQuery(name="Cotizacion.findAll", query="SELECT c FROM Cotizacion c")
public class Cotizacion implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="cotizacion_id_seq")
	@SequenceGenerator(name="cotizacion_id_seq", sequenceName="cotizacion_id_seq", initialValue=1, allocationSize=1)
	private Integer idCotizacion;
	
	@Column(name="fecha_creacion")
	private Date fechaCreacion;
	
	@Column(name="fecha_vencimiento")
	private Date fechaVencimiento;
	
	private String condiciones;
	
	private String estatus;
	
	private String mensaje;
	
	private Float precioFlete;
	
	@Transient
	private Float totalPrecioVenta=new Float(0);
	
	@Transient
	private Float totalFlete=new Float(0);
	
	@Transient
	private Float totalCotizacion=new Float(0);
	
	//bi-directional many-to-one association to Proveedor
	@ManyToOne
	@JoinColumn(name="id_proveedor")
	private Proveedor proveedor;
	
	//bi-directional many-to-one association to HistoricoMoneda
	@ManyToOne
	@JoinColumn(name="id_historico_moneda")
	private HistoricoMoneda historicoMoneda;
	
	//bi-directional one-to-many association to DetalleCotizacion
	@OneToMany(mappedBy="cotizacion",fetch=FetchType.LAZY) //cascade=CascadeType.REMOVE, orphanRemoval=true
	private List<DetalleCotizacion> detalleCotizacions;

	public Cotizacion() {
		this.detalleCotizacions = new ArrayList<DetalleCotizacion>();
		this.historicoMoneda = new HistoricoMoneda();
	}
	
	public Cotizacion(String mensaje){
		this.detalleCotizacions = new ArrayList<DetalleCotizacion>();
		this.historicoMoneda = new HistoricoMoneda();
		this.mensaje = mensaje;
	}

	public Cotizacion(Integer idCotizacion,	Date fechaCreacion, Date fechaVencimiento, 
			String estatus, String mensaje) {
		this.detalleCotizacions = new ArrayList<DetalleCotizacion>();
		this.historicoMoneda = new HistoricoMoneda();
		this.idCotizacion = idCotizacion;
		this.fechaCreacion = fechaCreacion;
		this.fechaVencimiento = fechaVencimiento;
		this.estatus = estatus;
		this.mensaje = mensaje;
	}

	public Cotizacion(Integer idCotizacion, Date fechaCreacion,
			Date fechaVencimiento, String estatus, String mensaje, Proveedor proveedor) {
		this.detalleCotizacions = new ArrayList<DetalleCotizacion>();
		this.historicoMoneda = new HistoricoMoneda();
		this.idCotizacion = idCotizacion;
		this.fechaCreacion = fechaCreacion;
		this.fechaVencimiento = fechaVencimiento;
		this.estatus = estatus;
		this.mensaje = mensaje;
		this.proveedor = proveedor;
	}
	
	public Cotizacion(Proveedor proveedor){
		this.proveedor = proveedor;
	}

	public Integer getIdCotizacion() {
		return idCotizacion;
	}

	public void setIdCotizacion(Integer idCotizacion) {
		this.idCotizacion = idCotizacion;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public String getCondiciones() {
		return condiciones;
	}

	public void setCondiciones(String condiciones) {
		this.condiciones = condiciones;
	}

	public String getEstatus() {
		return estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public Float getPrecioFlete() {
		return precioFlete;
	}

	public void setPrecioFlete(Float precioFlete) {
		this.precioFlete = precioFlete;
	}

	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	public HistoricoMoneda getHistoricoMoneda() {
		return historicoMoneda;
	}

	public void setHistoricoMoneda(HistoricoMoneda historicoMoneda) {
		this.historicoMoneda = historicoMoneda;
	}

	public List<DetalleCotizacion> getDetalleCotizacions() {
		return detalleCotizacions;
	}

	public void setDetalleCotizacions(List<DetalleCotizacion> detalleCotizacions) {
		this.detalleCotizacions = detalleCotizacions;
	}

	public DetalleCotizacion addDetalleCotizacion(DetalleCotizacion detalleCotizacion) {
		getDetalleCotizacions().add(detalleCotizacion);
		detalleCotizacion.setCotizacion(this);

		return detalleCotizacion;
	}

	public DetalleCotizacion removeDetalleCotizacion(DetalleCotizacion detalleCotizacion) {
		getDetalleCotizacions().remove(detalleCotizacion);
		detalleCotizacion.setCotizacion(null);

		return detalleCotizacion;
	}
	
	//Trasient
	public Float getTotalPrecioVenta() {
		return totalPrecioVenta;
	}

	public void setTotalPrecioVenta(Float totalPrecioVenta) {
		this.totalPrecioVenta = totalPrecioVenta;
	}

	public Float getTotalFlete() {
		return totalFlete;
	}

	public void setTotalFlete(Float totalFlete) {
		this.totalFlete = totalFlete;
	}
	
	public Float getTotalCotizacion() {
		return totalCotizacion;
	}

	public void setTotalCotizacion(Float totalCotizacion) {
		this.totalCotizacion = totalCotizacion;
	}
	
	/**EVENTOS*/
	@PrePersist
	public void prePersist(){
		if(this.historicoMoneda!=null && this.historicoMoneda.getIdHistoria()==null)
			this.historicoMoneda = null;
	}

	@PreUpdate
	public void preUpdate(){
		if(this.historicoMoneda!=null && this.historicoMoneda.getIdHistoria()==null)
			this.historicoMoneda = null;
	}
	
}
