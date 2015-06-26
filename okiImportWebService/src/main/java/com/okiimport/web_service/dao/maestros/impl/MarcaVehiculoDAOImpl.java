package com.okiimport.web_service.dao.maestros.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.Predicate;

import com.okiimport.web_service.dao.AbstractJpaDao;
import com.okiimport.web_service.dao.maestros.MarcaVehiculoDAO;
import com.okiimport.web_service.modelo.MarcaVehiculo;

public class MarcaVehiculoDAOImpl extends AbstractJpaDao<MarcaVehiculo, Integer> implements
		MarcaVehiculoDAO {

	public MarcaVehiculoDAOImpl() {
		super(MarcaVehiculo.class);
		// TODO Auto-generated constructor stub
	}
	
	public List<MarcaVehiculo> listaMarcasVehiculosActivas(Integer start,
			Integer limit) {
		// TODO Auto-generated method stub
		this.crearCriteria();
		this.crearJoins(null);
		List<Predicate> restrinciones = new ArrayList<Predicate>();
		restrinciones.add(this.criteriaBuilder.equal(this.entity.get("estatus"), "activo"));
		Map<String, Boolean> orders=new HashMap<String, Boolean>();
		return this.ejecutarCriteria(concatenaArrayPredicate(restrinciones), orders, start,limit);
	}
}
