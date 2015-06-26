package com.okiimport.web_service.dao.transaccion;

import java.util.List;

import com.okiimport.web_service.dao.IGenericDao;
import com.okiimport.web_service.modelo.Requerimiento;

public interface RequerimientoDAO extends IGenericDao<Requerimiento, Integer> {
	public List <Requerimiento> ConsultarRequerimientoUsuario(Requerimiento regFiltro, String fieldSort, Boolean sortDirection,
			Integer idusuario, List<String> estatus, int start, int limit);
	public List <Requerimiento> ConsultarRequerimientosCliente(Requerimiento regFiltro, String fieldSort, Boolean sortDirection, 
			String cedula, int start, int limit);
	public List <Requerimiento> ConsultarRequerimientosCotizados(Requerimiento regFiltro, String fieldSort, Boolean sortDirection,
			Integer idusuario, int start, int limit);
	public List<Requerimiento> ConsultarRequerimientosConSolicitudesCotizacion(Requerimiento regFiltro, String fieldSort, 
			Boolean sortDirection, Integer idProveedor, List<String> estatus, int start, int limit);
}
