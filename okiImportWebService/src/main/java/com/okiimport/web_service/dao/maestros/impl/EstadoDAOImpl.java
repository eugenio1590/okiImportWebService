package com.okiimport.web_service.dao.maestros.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.Predicate;

import com.okiimport.web_service.dao.AbstractJpaDao;
import com.okiimport.web_service.dao.maestros.EstadoDAO;
import com.okiimport.web_service.modelo.Estado;

public class EstadoDAOImpl extends AbstractJpaDao<Estado, Integer> implements
		EstadoDAO {

	public EstadoDAOImpl() {
		super(Estado.class);
		// TODO Auto-generated constructor stub
	}
	
	public List<Estado> listaEstadosActivos(Integer start,
			Integer limit) {
		// TODO Auto-generated method stub
		this.crearCriteria();
		this.crearJoins(null);
		List<Predicate> restrinciones = new ArrayList<Predicate>();
		Map<String, Boolean> orders=new HashMap<String, Boolean>();
		return this.ejecutarCriteria(concatenaArrayPredicate(restrinciones), orders, start,limit);
	}

	

}