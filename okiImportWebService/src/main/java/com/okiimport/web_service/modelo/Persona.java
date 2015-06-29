package com.okiimport.web_service.modelo;

import java.io.Serializable;

import javax.persistence.*;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;


/**
 * The persistent class for the persona database table.
 * 
 */
@Entity
@Table(name="persona")
@NamedQuery(name="Persona.findAll", query="SELECT p FROM Persona p")
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="person_type")
@JsonIgnoreProperties({"tipoMenu"})
public abstract class Persona implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="persona_id_seq")
	@SequenceGenerator(name="persona_id_seq", sequenceName="persona_id_seq", initialValue=1, allocationSize=1)
	@Column(name="id", unique=true, nullable=false)
	protected Integer id;

	protected String apellido;

	@Column(name="cedula", unique=true, nullable=false)
	protected String cedula;

	protected String correo;

	protected String direccion;

	protected String nombre;
	
	protected String telefono;
	
	@Column(name="tipo_menu")
	protected Integer tipoMenu;
	
	@Column(length=50)
	private String estatus;
	
	//bi-directional one-to-one association to Usuario (Relacion Poliformica)
	@OneToOne(mappedBy="persona")
	protected Usuario usuario;
	
	//bi-directional many-to-one association to Ciudad
	@ManyToOne
	@JoinColumn(name="id_ciudad")
	protected Ciudad ciudad;

	public Persona() {
	}
	
	public Persona(Persona persona){
		this(persona.getId(), persona.getApellido(), persona.getCedula(), persona.getCorreo(), persona.getDireccion(), 
				persona.getNombre(), persona.getTelefono(), persona.getUsuario(), persona.getEstatus());
	}

	public Persona(Integer id, String apellido, String cedula, String correo,
			String direccion, String nombre, String telefono, Usuario usuario, String estatus) {
		super();
		this.id = id;
		this.apellido = apellido;
		this.cedula = cedula;
		this.correo = correo;
		this.direccion = direccion;
		this.nombre = nombre;
		this.telefono = telefono;
		this.usuario = usuario;
		this.estatus = estatus;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getApellido() {
		return this.apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getCedula() {
		return this.cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getCorreo() {
		return this.correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public Ciudad getCiudad() {
		return ciudad;
	}

	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}

	public String getEstatus() {
		return estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	/**METODOS PROPIOS DE LA CLASE*/
	public static Persona getNewInstance(){
		return new Persona() {
			private static final long serialVersionUID = 1L;

			@Override
			public Integer getTipoMenu() {
				// TODO Auto-generated method stub
				return null;
			}
		};
	}
	
	public abstract Integer getTipoMenu();
}