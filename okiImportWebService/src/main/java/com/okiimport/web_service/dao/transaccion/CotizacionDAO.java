package com.okiimport.web_service.dao.transaccion;

import java.util.List;

import com.okiimport.web_service.dao.IGenericDao;
import com.okiimport.web_service.modelo.Cotizacion;

public interface CotizacionDAO extends IGenericDao<Cotizacion, Integer> {
	public List<Cotizacion> consultarCotizacionesAsignadas(Cotizacion cotizacion, String fieldSort, Boolean sortDirection,
			Integer idRequerimiento, List<String> estatus, int start, int limit);
			//Integer idRequerimiento, int start, int limit);
	public List<Cotizacion> consultarSolicitudCotizaciones(Cotizacion cotizacion, String fieldSort, Boolean sortDirection,
			Integer idRequerimiento, int idProveedor, List<String> estatus, int start, int limit);
}

