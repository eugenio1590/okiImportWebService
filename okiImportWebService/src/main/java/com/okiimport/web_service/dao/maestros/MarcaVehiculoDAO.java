package com.okiimport.web_service.dao.maestros;

import java.util.List;

import com.okiimport.web_service.dao.IGenericDao;
import com.okiimport.web_service.modelo.MarcaVehiculo;

public interface MarcaVehiculoDAO extends IGenericDao<MarcaVehiculo, Integer> {
	
	public List<MarcaVehiculo> listaMarcasVehiculosActivas(Integer start, Integer limit);

}
