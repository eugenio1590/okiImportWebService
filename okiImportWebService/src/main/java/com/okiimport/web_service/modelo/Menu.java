package com.okiimport.web_service.modelo;

import java.io.Serializable;

import javax.persistence.*;

import com.okiimport.web_service.lib.ModelNavbar;

import java.util.ArrayList;
import java.util.List;


/**
 * The persistent class for the menu database table.
 * 
 */
@Entity
@Table(name="menu")
@NamedQuery(name="Menu.findAll", query="SELECT m FROM Menu m")
public class Menu implements Serializable, ModelNavbar{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="menu_id_seq")
	@SequenceGenerator(name="menu_id_seq", sequenceName="menu_id_seq", initialValue=1, allocationSize=1)
	@Column(name="id_menu", unique=true, nullable=false)
	private Integer idMenu;

	@Column(length=255)
	private String actividad;

	@Column(length=255)
	private String icono;

	@Column(length=255)
	private String nombre;
	
	@Column
	private Integer tipo;

	@Column(length=255)
	private String ruta;

	//bi-directional many-to-one association to Menu
	@ManyToOne
	@JoinColumn(name="id_padre")
	private Menu padre;

	//bi-directional one-to-many association to Menu
	@OneToMany(mappedBy="padre")
	private List<Menu> hijos;

	public Menu() {
	}

	public Integer getIdMenu() {
		return this.idMenu;
	}

	public void setIdMenu(Integer idMenu) {
		this.idMenu = idMenu;
	}

	public String getActividad() {
		return this.actividad;
	}

	public void setActividad(String actividad) {
		this.actividad = actividad;
	}

	public String getIcono() {
		return this.icono;
	}

	public void setIcono(String icono) {
		this.icono = icono;
	}

	public String getNombre() {
		return this.nombre;
	}

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getRuta() {
		return this.ruta;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}

	public Menu getPadre() {
		return this.padre;
	}

	public void setPadre(Menu menu) {
		this.padre = menu;
	}

	public List<Menu> getHijos() {
		return this.hijos;
	}

	public void setHijos(List<Menu> hijos) {
		this.hijos = hijos;
	}

	public Menu addHijo(Menu menu) {
		getHijos().add(menu);
		menu.setPadre(this);

		return menu;
	}

	public Menu removeHijo(Menu menu) {
		getHijos().remove(menu);
		menu.setPadre(null);

		return menu;
	}

	/**INTERFAZ*/
	//1.ModelNavbar
	public String getLabel() {
		// TODO Auto-generated method stub
		return this.getNombre();
	}
	
	public String getIcon() {
		// TODO Auto-generated method stub
		return this.getIcono();
	}
	
	public String getUriLocation() {
		// TODO Auto-generated method stub
		return this.getRuta();
	}
	
	public List<ModelNavbar> getChilds() {
		// TODO Auto-generated method stub
		List<ModelNavbar> temp = new ArrayList<ModelNavbar>();
		if(this.hijos!=null)
			for(Menu menu : this.getHijos())
				temp.add(menu);
		return temp;
	}

}