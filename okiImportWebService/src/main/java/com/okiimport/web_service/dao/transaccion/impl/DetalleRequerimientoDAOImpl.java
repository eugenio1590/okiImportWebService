package com.okiimport.web_service.dao.transaccion.impl;

import com.okiimport.web_service.dao.AbstractJpaDao;
import com.okiimport.web_service.modelo.DetalleRequerimiento;
import com.okiimport.web_service.dao.transaccion.DetalleRequerimientoDAO;

public class DetalleRequerimientoDAOImpl extends AbstractJpaDao<DetalleRequerimiento, Integer>
		implements DetalleRequerimientoDAO {

	public DetalleRequerimientoDAOImpl() {
		super(DetalleRequerimiento.class);
		// TODO Auto-generated constructor stub
	}

}
