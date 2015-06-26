package com.okiimport.web_service.dao.maestros.impl;

import java.util.List;

import javax.persistence.criteria.Predicate;

import com.okiimport.web_service.dao.maestros.ClienteDAO;
import com.okiimport.web_service.modelo.Cliente;

public class ClienteDAOImpl extends PersonaDAOImpl<Cliente> implements ClienteDAO {

	public ClienteDAOImpl() {
		super(Cliente.class);
		// TODO Auto-generated constructor stub
	}

	/**METODOS OVERRIDE*/
	@Override
	protected void agregarRestriccionesPersona(Cliente persona, List<Predicate> restricciones) {
		// TODO Auto-generated method stub
		
	}
}
