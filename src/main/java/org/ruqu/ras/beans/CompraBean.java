package org.ruqu.ras.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.TimeZone;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.ToggleEvent;
import org.ruqu.ras.domain.Categoria;
import org.ruqu.ras.domain.Compra;
import org.ruqu.ras.domain.Detallecompra;
import org.ruqu.ras.domain.DetallecompraId;
import org.ruqu.ras.domain.Equipo;
import org.ruqu.ras.domain.Proveedor;
import org.ruqu.ras.helpers.utils.FacesMessageHelper;
import org.ruqu.ras.service.ICategoriaService;
import org.ruqu.ras.service.ICompraService;
import org.ruqu.ras.service.IDetalleCompraService;
import org.ruqu.ras.service.IEquipoService;
import org.ruqu.ras.service.IProveedorService;

@ManagedBean(name = "CompraBean")
@ViewScoped
public class CompraBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String growlPath ="form:growl";
	
	@ManagedProperty(value="#{EquipoService}")
	private IEquipoService equipoService;
	
	@ManagedProperty(value = "#{ProveedorService}")
	private IProveedorService proveedorService; 
	
	@ManagedProperty(value = "#{CategoriaService}")
	private ICategoriaService categoriaService;  

	@ManagedProperty(value = "#{CompraService}")
	private ICompraService compraService;
	
	@ManagedProperty(value = "#{DetalleCompraService}")
	private IDetalleCompraService detalleCompraService;
	
	private List<Equipo> equipos;
	private List<Categoria> categorias;
	private List<Proveedor> proveedores;
	private List<Compra> compras;
	private List<Detallecompra> detalleCompras;
	
	Compra compra = new Compra();
	Compra compraNuevo = new Compra();
	Detallecompra detCompra = new Detallecompra();
	Proveedor provCompra = new Proveedor();
	Detallecompra detalleSelected = new Detallecompra();
	
	private boolean accionEditar = true;

	

	public CompraBean(){
		equipos = new ArrayList<Equipo>();
		categorias = new ArrayList<Categoria>();
		proveedores = new ArrayList<Proveedor>();
		compras = new ArrayList<Compra>();
		detalleCompras = new ArrayList<Detallecompra>();
	}
	
	@PostConstruct
	public void init(){
		equipos=getEquipoService().getEquipos();
		categorias=getCategoriaService().getCategorias();
		proveedores=getProveedorService().getProveedors();
		compras=getCompraService().getCompras();
	}
	
	
	/* AJAX BUTTON EVENTS  
	*  ==================
	*/
	
	public void nuevoEvent(){
		accionEditar=false;
		limpiarCampos();
	}
	
	
	public void editarEvent(){
		accionEditar=true;
		try{
			if(getCompraNuevo()!=null){
				setCompra(getCompraService().getCompraById(getCompraNuevo().getIdCompra()));				
			}
			else{
				FacesMessageHelper.sendGrowlMessage(FacesMessage.SEVERITY_WARN, "Error", "Seleccione un registro",growlPath);
			}
		}catch(Exception e){
			System.out.println("ERROR: "+e.getMessage());
			accionEditar=false;
		}
	}
	
	public void eliminarEvent(){
		if(getCompraNuevo()!=null){
			
		}
		else{
			FacesMessageHelper.sendGrowlMessage(FacesMessage.SEVERITY_WARN, "Error", "Seleccione un registro",growlPath);
		}
	}
	

	public void onEdit(RowEditEvent event) {
		Compra c=(Compra)event.getObject();
		setCompra(compraService.getCompraById(c.getIdCompra()));
		
		getCompra().setIdCompra(c.getIdCompra());
		getCompra().setFecha(c.getFecha());
		getCompra().setTotal(c.getTotal());
		getCompra().setProveedor(proveedorService.getProveedorById(c.getProveedor().getIdProveedor()));
		getCompra().setDetallecompras(c.getDetallecompras());
		
		validarEditar();
    }  
    
	public void onCancel(RowEditEvent event) {  
	    FacesMessageHelper.sendGrowlMessage(FacesMessage.SEVERITY_INFO, "Edición Cancelada", ((Compra) event.getObject()).getIdCompra().toString(),growlPath);
	}
	
	public void onRowToggle(ToggleEvent event) {
		
		if(event.getVisibility().name().compareToIgnoreCase("hidden") != 0){
			
			Compra v=(Compra)event.getData();
			setCompra(compraService.getCompraById(v.getIdCompra()));
			
			setDetalleCompras(new ArrayList<Detallecompra>(getCompra().getDetallecompras()));
		}
		RequestContext.getCurrentInstance().execute("test();");

    }
	
	//BOTON PROCESAR-DIALOG
	public void procesarDialog(){
		if(accionEditar){
			validarEditar();
		}else{
			validarNuevo();
		}
		RequestContext.getCurrentInstance().execute("dialog.hide();");
		refrescarCompras();
		limpiarCampos();
	}
	

	/* ACCIONES CRUD
	*  =============
	*/
	
	public void insertar(){
		getCompraService().addCompra(getCompra());		
	}
	
	public void editar(){
		getCompraService().updateCompra(getCompra());
	}
	
	public void eliminar(){
		getCompraService().deleteCompraLogico(getCompraNuevo());
		refrescarCompras();
	}
	
	public void validarNuevo(){
		RequestContext context = RequestContext.getCurrentInstance();  
        FacesMessage msg = null;  
        boolean registrado = false;  

        if(getCompra().getProveedor()!=null && getCompra().getDetallecompras()!=null && detalleCompras.size()>0){
        	registrado=true;
            registrarCompra();
        	msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "COMPRA REGISTRADA", getCompra().getIdCompra().toString());
        } 
		else if(detalleCompras.size() == 0){
			registrado = false;  
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error de registro", "Nada que ingresar.");  
		}
		else{  
        	registrado = false;  
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error de registro", "Campo(s) inválido(s)");  
        }
        FacesContext.getCurrentInstance().addMessage(null, msg);  
        context.addCallbackParam("registrado", registrado);
	}
	

	public void validarEditar(){
		RequestContext context = RequestContext.getCurrentInstance();  
        FacesMessage msg = null;  
        boolean editado = false;  
        
        if(getCompra().getProveedor()!=null && getCompra().getDetallecompras()!=null){
        	editado=true;
        	msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "COMPRA EDITADA", getCompra().getIdCompra().toString());
            editar();
        }else if(getCompraNuevo()==null){
        	editado = false;  
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Seleccione un registro");  
        }else{
        	editado = false;  
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Nombre inválido");  
        }
        FacesContext.getCurrentInstance().addMessage(null, msg);  
        context.addCallbackParam("editado", editado);  
	}
	
	public void validarEliminar(){
		RequestContext context = RequestContext.getCurrentInstance();  
        FacesMessage msg = null;  
        boolean eliminado = false;  

        if(getCompraNuevo()!=null){
        	eliminado = true;  
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "COMPRA ELIMINADA", getCompraNuevo().getIdCompra().toString());
            eliminar();
        } else {  
			System.out.println("NOOO");
        	eliminado = false;  
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Seleccione un registro");  
        }  
        
        FacesContext.getCurrentInstance().addMessage(null, msg);  
        context.addCallbackParam("eliminado", eliminado);
	}
	
	/*
	*	RUTINAS ADICIONALES
	*	===================
    */
	public void showError(){
		FacesMessageHelper.sendGrowlMessage(FacesMessage.SEVERITY_WARN, "Registro","Campos resaltados invalidos", growlPath
				,"Error");		
	}

	public void limpiarCampos()
	{
		compra = new Compra();		
		compraNuevo = new Compra();
		detalleCompras = new ArrayList<Detallecompra>();
		detCompra = new Detallecompra();
	}

	public void refrescarCompras()
	{
		setCompras(getCompraService().getCompras());
	}

	
	public void comprarEquipo(){
		
		if(detCompra.getCantidad() == 0){
			FacesMessageHelper.sendGrowlMessage(FacesMessage.SEVERITY_WARN, "Aviso", "Ingrese cantidad válida", growlPath);
			return;
		}
		else{
			detCompra.setId(new DetallecompraId(0, detCompra.getEquipo().getIdEquipo()));
			detCompra.setTotalDetalle(BigDecimal.valueOf(detCompra.getEquipo().getCosto().intValue() * detCompra.getCantidad()));
			detCompra.setCompra(compra);
			
			if(!detalleCompras.contains(detCompra)){				
				detalleCompras.add(detCompra);
				detCompra = new Detallecompra();
			}
			else{
				Detallecompra temp = (Detallecompra) detalleCompras.get(detalleCompras.indexOf(detCompra));
				temp.setCantidad(temp.getCantidad()+detCompra.getCantidad());
				temp.setTotalDetalle(temp.getTotalDetalle().add(detCompra.getTotalDetalle()));
			}
						
			for(Detallecompra dt:detalleCompras){
				System.out.print("\n\n"+dt.getEquipo().getCodigo());
				System.out.print("\\"+dt.getCantidad());
				System.out.print("\\"+dt.getEquipo().getStock()+"\n\n");
			}
		}
	}
	
	public void registrarCompra(){
		
		double costoTotal = 0;
		
		for(Detallecompra dt:detalleCompras){
			dt.getEquipo().getCodigo();
			costoTotal = costoTotal + dt.getEquipo().getCosto().doubleValue() * dt.getCantidad();
		}
		
		getCompra().setFecha(new Date());
	//	System.out.print("\n\n\n"+getCompra().getProveedor() +"\n\n\n");
		getCompra().setProveedor(getProveedorService().getProveedorById(getCompra().getProveedor().getIdProveedor()));
		getCompra().setTotal(BigDecimal.valueOf(costoTotal));
		getCompra().setDetallecompras(new HashSet<Detallecompra>(detalleCompras));

		getCompraService().addCompra(getCompra());

		System.out.print("\n\n"+getCompra().getFecha().toString());
		System.out.print("\n\n"+getCompra().getIdCompra());
		System.out.print("\n\n"+getCompra().getTotal());
		System.out.print("\n\n"+getCompra().getProveedor().getNombre());
		
		for(Detallecompra dt:getCompra().getDetallecompras()){
			dt.setCompra(getCompra());
			dt.setId(new DetallecompraId(getCompra().getIdCompra(),dt.getEquipo().getIdEquipo()));
			System.out.print("\n\n"+dt.getCompra().getIdCompra());
			dt.getEquipo().setStock(dt.getEquipo().getStock() + dt.getCantidad());
			getEquipoService().updateEquipo(dt.getEquipo());
		}
		
		getCompraService().updateCompra(getCompra());
		
		for(Detallecompra dt:getCompra().getDetallecompras()){
			getDetalleCompraService().addDetallecompra(dt);
		}
		
		detalleCompras = new ArrayList<Detallecompra>();
	}

	public void timeZone(){
		TimeZone.getDefault();
	}

	public IEquipoService getEquipoService() {
		return equipoService;
	}

	public void setEquipoService(IEquipoService equipoService) {
		this.equipoService = equipoService;
	}

	public IProveedorService getProveedorService() {
		return proveedorService;
	}

	public void setProveedorService(IProveedorService proveedorService) {
		this.proveedorService = proveedorService;
	}

	public ICategoriaService getCategoriaService() {
		return categoriaService;
	}

	public void setCategoriaService(ICategoriaService categoriaService) {
		this.categoriaService = categoriaService;
	}

	public ICompraService getCompraService() {
		return compraService;
	}

	public void setCompraService(ICompraService compraService) {
		this.compraService = compraService;
	}

	public List<Equipo> getEquipos() {
		return equipos;
	}

	public void setEquipos(List<Equipo> equipos) {
		this.equipos = equipos;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	public List<Proveedor> getProveedores() {
		return proveedores;
	}

	public void setProveedores(List<Proveedor> proveedores) {
		this.proveedores = proveedores;
	}

	public List<Compra> getCompras() {
		return compras;
	}

	public void setCompras(List<Compra> compras) {
		this.compras = compras;
	}

	public Compra getCompra() {
		return compra;
	}

	public void setCompra(Compra compra) {
		this.compra = compra;
	}

	public Compra getCompraNuevo() {
		return compraNuevo;
	}

	public void setCompraNuevo(Compra compraNuevo) {
		this.compraNuevo = compraNuevo;
	}

	public boolean isAccionEditar() {
		return accionEditar;
	}

	public void setAccionEditar(boolean accionEditar) {
		this.accionEditar = accionEditar;
	}
	

	public List<Detallecompra> getDetalleCompras() {
		return detalleCompras;
	}

	public void setDetalleCompras(List<Detallecompra> detalleCompras) {
		this.detalleCompras = detalleCompras;
	}
	
	public Detallecompra getDetCompra() {
		return detCompra;
	}

	public void setDetCompra(Detallecompra detCompra) {
		this.detCompra = detCompra;
	}
	
	public IDetalleCompraService getDetalleCompraService() {
		return detalleCompraService;
	}

	public void setDetalleCompraService(IDetalleCompraService detalleCompraService) {
		this.detalleCompraService = detalleCompraService;
	}
	
	public Proveedor getProvCompra() {
		return provCompra;
	}

	public void setProvCompra(Proveedor provCompra) {
		this.provCompra = provCompra;
	}
	

	public Detallecompra getDetalleSelected() {
		return detalleSelected;
	}

	public void setDetalleSelected(Detallecompra detalleSelected) {
		this.detalleSelected = detalleSelected;
	}
	
	public String etiBotonDialog(){
		if(accionEditar){
			return "EDITAR";
		}else{
			return "NUEVO";
		}
	}


}
