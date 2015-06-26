package com.okiimport.web_service.dao.maestros;

import java.util.List;

import com.okiimport.web_service.dao.IGenericDao;
import com.okiimport.web_service.modelo.Persona;

public interface PersonaDAO<T extends Persona> extends IGenericDao<T, Integer> {
	public T consultarPersona(T persona);
	public List<T> consultarPersonaSinUsuarios(T persona, String fieldSort, Boolean sortDirection, int start, int limit);
}
