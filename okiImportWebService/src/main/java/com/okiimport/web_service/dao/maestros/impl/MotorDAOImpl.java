package com.okiimport.web_service.dao.maestros.impl;

import com.okiimport.web_service.dao.AbstractJpaDao;
import com.okiimport.web_service.dao.maestros.MotorDAO;
import com.okiimport.web_service.modelo.Motor;

public class MotorDAOImpl extends AbstractJpaDao<Motor, Integer> implements MotorDAO {

	public MotorDAOImpl() {
		super(Motor.class);
		// TODO Auto-generated constructor stub
	}

}
