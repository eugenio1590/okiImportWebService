package com.okiimport.web_service.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;


/**
 * The persistent class for the proveedor database table.
 * 
 */
@Entity
@Table(name="proveedor")
@NamedQuery(name="Proveedor.findAll", query="SELECT p FROM Proveedor p")
@PrimaryKeyJoinColumn(name="id_proveedor")
@JsonIgnoreProperties({"tipoMenu"})
public class Proveedor extends Persona implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Column
	private Boolean tipoProveedor;
	
	//bi-directional one-to-many association to Cotizacion
	@OneToMany(mappedBy="proveedor", fetch=FetchType.LAZY, orphanRemoval=true)
	private List<Cotizacion> cotizacions;
		
	//bi-directional many-to-many association to MarcaRepuesto
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(
		name="proveedor_marca_vehiculo"
		, joinColumns={
				@JoinColumn(name="id_proveedor")
		}
		, inverseJoinColumns={
				@JoinColumn(name="id_marca_vehiculo")
		}
	)
	private List<MarcaVehiculo> marcaVehiculos;


	//bi-directional many-to-many association to MarcaRepuesto
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(
		name="proveedor_clasificacion_repuesto"
		, joinColumns={
				@JoinColumn(name="id_proveedor")
		}
		, inverseJoinColumns={
				@JoinColumn(name="id_clasificacion_repuesto")
		}
	)
	private List<ClasificacionRepuesto> clasificacionRepuestos;
		
	public Proveedor() {
		marcaVehiculos = new ArrayList<MarcaVehiculo>();
		clasificacionRepuestos = new ArrayList<ClasificacionRepuesto>();
	}
	
	public Proveedor(Persona persona) {
		super(persona);
		marcaVehiculos = new ArrayList<MarcaVehiculo>();
		clasificacionRepuestos = new ArrayList<ClasificacionRepuesto>();
	}
	
	public Proveedor(String cedula) {
		super.cedula = cedula;
		marcaVehiculos = new ArrayList<MarcaVehiculo>();
		clasificacionRepuestos = new ArrayList<ClasificacionRepuesto>();
	}
	
	public Proveedor(Integer id,String cedula, String correo,
			String direccion, String nombre, String telefono, String estatus) {
		super(id, null, cedula, correo, direccion, nombre, telefono, null, estatus);
	}

	public Boolean getTipoProveedor() {
		return tipoProveedor;
	}

	public void setTipoProveedor(Boolean tipoProveedor) {
		this.tipoProveedor = tipoProveedor;
	}

	public List<Cotizacion> getCotizacions() {
		return cotizacions;
	}

	public void setCotizacions(List<Cotizacion> cotizacions) {
		this.cotizacions = cotizacions;
	}
	
	public Cotizacion addCotizacion(Cotizacion cotizacion) {
		getCotizacions().add(cotizacion);
		cotizacion.setProveedor(this);

		return cotizacion;
	}

	public Cotizacion removeDetalleRequerimiento(Cotizacion cotizacion) {
		getCotizacions().remove(cotizacion);
		cotizacion.setProveedor(null);

		return cotizacion;
	}

	public List<MarcaVehiculo> getMarcaVehiculos() {
		return marcaVehiculos;
	}

	public void setMarcaVehiculos(List<MarcaVehiculo> marcaVehiculos) {
		this.marcaVehiculos = marcaVehiculos;
	}

	public List<ClasificacionRepuesto> getClasificacionRepuestos() {
		return clasificacionRepuestos;
	}

	public void setClasificacionRepuestos(
			List<ClasificacionRepuesto> clasificacionRepuestos) {
		this.clasificacionRepuestos = clasificacionRepuestos;
	}

	/**METODOS OVERRIDE*/
	@Override
	public Integer getTipoMenu() {
		// TODO Auto-generated method stub
		return this.tipoMenu=3;
	}
	
	/**METODOS PROPIOS DE LA CLASE*/
	public String ubicacion(String separador){
		String ubicacion = "";
		if(ciudad!=null){
			Estado estado = ciudad.getEstado();
			if(estado!=null)
				ubicacion += estado.getNombre()+separador;
			
			ubicacion+= ciudad.getNombre();
		}
		return ubicacion;
	}
}