package com.okiimport.web_service.modelo;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the motor database table.
 * 
 */
@Entity
@Table(name="motor")
@NamedQuery(name="Motor.findAll", query="SELECT m FROM Motor m")
public class Motor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="motor_id_seq")
	@SequenceGenerator(name="motor_id_seq", sequenceName="motor_id_seq", initialValue=1, allocationSize=1)
	@Column(name="id_motor")
	private Integer idMotor;

	private String nombre;

	//bi-directional one-to-many association to Requerimiento
	@OneToMany(mappedBy="motor")
	private List<Requerimiento> requerimientos;

	public Motor() {
	}
	
	public Motor(String nombre){
		this.nombre = nombre;
	}

	public Integer getIdMotor() {
		return this.idMotor;
	}

	public void setIdMotor(Integer idMotor) {
		this.idMotor = idMotor;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Requerimiento> getRequerimientos() {
		return this.requerimientos;
	}

	public void setRequerimientos(List<Requerimiento> requerimientos) {
		this.requerimientos = requerimientos;
	}

	public Requerimiento addRequerimiento(Requerimiento requerimiento) {
		getRequerimientos().add(requerimiento);
		requerimiento.setMotor(this);

		return requerimiento;
	}

	public Requerimiento removeRequerimiento(Requerimiento requerimiento) {
		getRequerimientos().remove(requerimiento);
		requerimiento.setMotor(null);

		return requerimiento;
	}

}