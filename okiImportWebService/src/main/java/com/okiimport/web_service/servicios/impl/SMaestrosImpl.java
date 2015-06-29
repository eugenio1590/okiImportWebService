package com.okiimport.web_service.servicios.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.okiimport.web_service.dao.maestros.AnalistaDAO;
import com.okiimport.web_service.dao.maestros.CiudadDAO;
import com.okiimport.web_service.dao.maestros.ClasificacionRepuestoDAO;
import com.okiimport.web_service.dao.maestros.ClienteDAO;
import com.okiimport.web_service.dao.maestros.EstadoDAO;
import com.okiimport.web_service.dao.maestros.MarcaVehiculoDAO;
import com.okiimport.web_service.dao.maestros.MotorDAO;
import com.okiimport.web_service.dao.maestros.PersonaDAO;
import com.okiimport.web_service.dao.maestros.ProveedorDAO;

import com.okiimport.web_service.servicios.SMaestros;
import com.okiimport.web_service.modelo.Analista;
import com.okiimport.web_service.modelo.ClasificacionRepuesto;
import com.okiimport.web_service.modelo.Cliente;
import com.okiimport.web_service.modelo.Persona;
import com.okiimport.web_service.modelo.MarcaVehiculo;
import com.okiimport.web_service.modelo.Proveedor;
import com.okiimport.web_service.modelo.Usuario;
import com.okiimport.web_service.lib.BeanInjector;

public class SMaestrosImpl extends AbstractServiceImpl implements SMaestros {

	@Autowired
	@BeanInjector("marcaVehiculoDAO")
	private MarcaVehiculoDAO marcaVehiculoDAO;

	@Autowired
	@BeanInjector("estadoDAO")
	private EstadoDAO estadoDAO;

	@Autowired
	@BeanInjector("ciudadDAO")
	private CiudadDAO ciudadDAO;

	@Autowired
	@BeanInjector("clienteDAO")
	private ClienteDAO clienteDAO;

	@Autowired
	@BeanInjector("analistaDAO")
	private AnalistaDAO analistaDAO;

	@Autowired
	@BeanInjector("proveedorDAO")
	private ProveedorDAO proveedorDAO;

	@Autowired
	@BeanInjector("clasificacionRepuestoDAO")
	private ClasificacionRepuestoDAO clasificacionRepuestoDAO;

	@Autowired
	@BeanInjector("motorDAO")
	private MotorDAO motorDAO;

	//Marcas
	public Map<String, Object> ConsultarMarcas(Integer page, Integer limit) {
		Map<String, Object> Parametros= new HashMap<String, Object>();
		if(limit==null)
			limit=-1;
		Parametros.put("total", marcaVehiculoDAO.listaMarcasVehiculosActivas(0, -1).size());
		Parametros.put("marcas", marcaVehiculoDAO.listaMarcasVehiculosActivas(page*limit, limit));
		return Parametros;
	}
	
	public MarcaVehiculo registrarOActualizarMarcaVehiculo(MarcaVehiculo marca){
		if(marca.getIdMarcaVehiculo()==null)
			marca = marcaVehiculoDAO.save(marca);
		else
			marca = marcaVehiculoDAO.update(marca);
		return marca;
	}

	//Estados
	public Map<String, Object> ConsultarEstado(Integer page, Integer limit) {
		if(limit==null)
			limit = -1;
		Map<String, Object> Parametros= new HashMap<String, Object>();
		Parametros.put("total", estadoDAO.listaEstadosActivos(0, -1).size());
		Parametros.put("estados", estadoDAO.listaEstadosActivos(page*limit, limit));
		return Parametros;
	}

	//Ciudades
	public Map<String, Object> ConsultarCiudad(Integer idEstado,Integer page, Integer limit) {
		if(limit==null)
			limit = -1;
		Map<String, Object> Parametros= new HashMap<String, Object>();
		Parametros.put("total", ciudadDAO.listaCiudadesActivas(idEstado, 0, -1).size());
		Parametros.put("ciudades", ciudadDAO.listaCiudadesActivas(idEstado,page*limit, limit));
		return Parametros;
	}

	//Persona
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public <T extends Persona> T acutalizarPersona(T persona){
		PersonaDAO dao = determinarPersonaDAO(persona.getClass());
		if(dao!=null){
			if(persona.getId()!=null)
				persona = (T) dao.update(persona);
			else
				persona = (T) dao.save(persona);
		}
		return persona;
	}

	//Cliente
	public Cliente registrarOActualizarCliente(Cliente cliente) {
		Cliente temp = clienteDAO.consultarPersona(new Cliente(cliente.getCedula()));
		if (temp==null)
			cliente=clienteDAO.save(cliente);
		else{
			cliente.setId(temp.getId());
			cliente=clienteDAO.update(cliente);
		}
		return cliente;
	}

	public Cliente consultarCliente(Cliente cliente) {
		return clienteDAO.consultarPersona(cliente);
	}

	//Analista
	public Map<String, Object> consultarAnalistasSinUsuarios(Persona personaF,  String fieldSort, Boolean sortDirection, 
			int pagina, int limit){
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("total", analistaDAO.consultarAnalistasSinUsuarios(personaF, fieldSort, sortDirection, 0, -1).size());
		parametros.put("analistas", analistaDAO.consultarAnalistasSinUsuarios(personaF, fieldSort, sortDirection, pagina*limit, limit));
		return parametros;
	}

	public Map<String, Object> consultarAdministradoresSinUsuarios(Persona personaF,  String fieldSort, Boolean sortDirection, 
			int pagina, int limit){
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("total", analistaDAO.consultarAdministradoresSinUsuarios(personaF, fieldSort, sortDirection, 0, -1).size());
		parametros.put("administradores", analistaDAO.consultarAdministradoresSinUsuarios(personaF, fieldSort, sortDirection, pagina*limit, limit));
		return parametros;
	}

	public List<Analista> consultarCantRequerimientos(List<String> estatus, int page, int limit){
		return analistaDAO.consultarCantRequerimientos(estatus, page, limit);
	}

	public Map<String, Object> consultarAnalistas(Analista analista, int page,
			int limit) {
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("total", Long.valueOf(analistaDAO.countAll()).intValue());
		parametros.put("analistas", analistaDAO.findAll(page*limit, limit));
		return parametros;
	}

	public Analista registrarAnalista(Analista analista) {
		return analistaDAO.save(analista);
	}


	//Proveedores
	public Map<String, Object> consultarProveedoresSinUsuarios(Persona personaF, String fieldSort, Boolean sortDirection,
			int pagina, int limit){
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("total", proveedorDAO.consultarProveedoresSinUsuarios(personaF, fieldSort, sortDirection, 0, -1).size());
		parametros.put("proveedores", proveedorDAO.consultarProveedoresSinUsuarios(personaF, fieldSort, sortDirection, pagina*limit, limit));
		return parametros;
	}

	public Map<String, Object> ConsultarClasificacionRepuesto(Integer page, Integer limit) {
		Map<String, Object> Parametros= new HashMap<String, Object>();
		Parametros.put("total", ((Long)clasificacionRepuestoDAO.countAll()).intValue());
		Parametros.put("clasificacionRepuesto", clasificacionRepuestoDAO.findAll(page*limit, limit));
		return Parametros;
	}

	public Proveedor registrarProveedor(Proveedor proveedor) {
		for(ClasificacionRepuesto clasificacion : proveedor.getClasificacionRepuestos())
			clasificacion.getProveedores().add(proveedor);
		for(MarcaVehiculo marca : proveedor.getMarcaVehiculos())
			marca.getProveedores().add(proveedor);
		return proveedorDAO.save(proveedor);
	}

	public Map<String, Object> ConsultarMotor(Integer page, Integer limit) {
		Map<String, Object> Parametros= new HashMap<String, Object>();
		Parametros.put("total", ((Long)motorDAO.countAll()).intValue());
		Parametros.put("motor", motorDAO.findAll(page*limit, limit));
		return Parametros;
	}

	public Map<String, Object> ConsultarProveedoresListaClasificacionRepuesto(Persona persona, String fieldSort, Boolean sortDirection,
			List<Integer> idsClasificacionRepuesto, int start, int limit){
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("total", proveedorDAO.consultarProveedoresListaClasificacionRepuesto(persona, fieldSort, sortDirection, idsClasificacionRepuesto, 0, -1).size());
		parametros.put("proveedores", proveedorDAO.consultarProveedoresListaClasificacionRepuesto(persona, fieldSort, sortDirection,idsClasificacionRepuesto, start*limit, limit));
		return parametros;
	}

	/**METODOS PROPIOS DE LA CLASE*/
	@SuppressWarnings({ "rawtypes", "unused", "unchecked" })
	private <T extends PersonaDAO, Y extends Persona> T determinarPersonaDAO(Class<Y> clase){
		T dao = null;
		if(clase != null){
			if(clase.equals(Analista.class))
				dao = (T) this.analistaDAO;
			else if(clase.equals(Proveedor.class))
				dao = (T) this.proveedorDAO;
			else if(clase.equals(Cliente.class))
				dao = (T) this.clienteDAO;
		}
		return dao;
	}

	/**SETTERS Y GETTERS*/
	public MarcaVehiculoDAO getMarcaVehiculoDAO() {
		return marcaVehiculoDAO;
	}
	public void setMarcaVehiculoDAO(MarcaVehiculoDAO marcaVehiculoDAO) {
		this.marcaVehiculoDAO = marcaVehiculoDAO;
	}

	public ClienteDAO getClienteDAO() {
		return clienteDAO;
	}

	public void setClienteDAO(ClienteDAO clienteDAO) {
		this.clienteDAO = clienteDAO;
	}

	public AnalistaDAO getAnalistaDAO() {
		return analistaDAO;
	}

	public void setAnalistaDAO(AnalistaDAO analistaDAO) {
		this.analistaDAO = analistaDAO;
	}

	public ProveedorDAO getProveedorDAO() {
		return proveedorDAO;
	}

	public void setProveedorDAO(ProveedorDAO proveedorDAO) {
		this.proveedorDAO = proveedorDAO;
	}

	public ClasificacionRepuestoDAO getClasificacionRepuestoDAO() {
		return clasificacionRepuestoDAO;
	}

	public void setClasificacionRepuestoDAO(
			ClasificacionRepuestoDAO clasificacionRepuestoDAO) {
		this.clasificacionRepuestoDAO = clasificacionRepuestoDAO;
	}

	public MotorDAO getMotorDAO() {
		return motorDAO;
	}

	public void setMotorDAO(MotorDAO motorDAO) {
		this.motorDAO = motorDAO;
	}
}
