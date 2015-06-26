package com.okiimport.web_service.dao.maestros;

import java.util.List;

import com.okiimport.web_service.dao.IGenericDao;
import com.okiimport.web_service.modelo.Ciudad;

public interface CiudadDAO extends IGenericDao<Ciudad, Integer> {
	
	public List<Ciudad> listaCiudadesActivas(Integer idEstado, Integer start, Integer limit);

}
