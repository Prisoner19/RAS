package org.ruqu.ras.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;
import org.ruqu.ras.domain.Proveedor;
import org.ruqu.ras.helpers.utils.FacesMessageHelper;
import org.ruqu.ras.service.IProveedorService;

@ManagedBean(name="ProveedorBean")
@ViewScoped
public class ProveedorBean implements Serializable{

	/**
	 * Beans' attributes
	 * =================
	 */
	private static final long serialVersionUID = 1L;
	private static final String growlPath ="form:growl";
	
	@ManagedProperty(value="#{ProveedorService}")
	IProveedorService proveedorService;	
	
	List<Proveedor> proveedors;
	
	Proveedor proveedor=new Proveedor();
	Proveedor proveedorNuevo=new Proveedor();
	
	private boolean accionEditar = true;




 	/* CONSTRUCTOR AND POSTCONSTRUCT 
	*  ============================
 	*/
	
	public ProveedorBean(){
		proveedors=new ArrayList<Proveedor>();
	}
	
	@PostConstruct
	public void init(){
		proveedors=getProveedorService().getProveedors();
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
			if(getProveedorNuevo()!=null){
				setProveedor(getProveedorService().getProveedorById(getProveedorNuevo().getIdProveedor()));
				RequestContext.getCurrentInstance().execute("dialog.show()");
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
		if(getProveedorNuevo()!=null){
			eliminar();
			FacesMessageHelper.sendGrowlMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Registro Eliminado "+proveedorNuevo.getNombre(),growlPath);
			refrescarProveedors();
		}
		else{
			FacesMessageHelper.sendGrowlMessage(FacesMessage.SEVERITY_WARN, "Error", "Seleccione un registro",growlPath);
		}
		
	}
	
	public void onEdit(RowEditEvent event) {
		Proveedor c=(Proveedor)event.getObject();
		setProveedor(proveedorService.getProveedorById(c.getIdProveedor()));
		
		getProveedor().setNombre(c.getNombre());
		getProveedor().setDireccion(c.getDireccion());
		getProveedor().setTelefono(c.getTelefono());
		editar();
		FacesMessageHelper.sendGrowlMessage(FacesMessage.SEVERITY_INFO, "Edicion Proveedor Realizada", getProveedor().getNombre(),growlPath);
    }  
      
    public void onCancel(RowEditEvent event) {  
        FacesMessageHelper.sendGrowlMessage(FacesMessage.SEVERITY_INFO, "Edicion Proveedor Cancelada", ((Proveedor) event.getObject()).getNombre(),growlPath);
       
    }
	//BOTON PROCESAR-DIALOG
	public void procesarDialog(){
		if(accionEditar){	
			editar();
			FacesMessageHelper.sendGrowlMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Registro Editado "+proveedor.getNombre(),growlPath);
		}else{
			insertar();
			FacesMessageHelper.sendGrowlMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Registro Nuevo "+proveedor.getNombre(),growlPath);
		}
		RequestContext.getCurrentInstance().execute("dialog.hide();");
		limpiarCampos();
		refrescarProveedors();
	}
	
	/* ACCIONES CRUD
	*  =============
	*/
	
	public void insertar(){
		getProveedorService().addProveedor(getProveedor());		
	}
	
	public void editar(){		
		getProveedorService().updateProveedor(getProveedor());		
	}
	
	public void eliminar(){
		getProveedorService().deleteProveedorLogico(getProveedorNuevo());	
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
		proveedor= new Proveedor();		
		proveedorNuevo = new Proveedor();
	}

	private void refrescarProveedors()
	{
		setProveedors(getProveedorService().getProveedors());
		
	}
	

	/* SETTER AND GETTERS
	*  ==================
	*/
	
	public Proveedor getProveedor() {
		return proveedor;
	}

	public IProveedorService getProveedorService() {
		return proveedorService;
	}

	public void setProveedorService(IProveedorService proveedorService) {
		this.proveedorService = proveedorService;
	}

	public List<Proveedor> getProveedors() {
		return proveedors;
	}

	public void setProveedors(List<Proveedor> proveedors) {
		this.proveedors = proveedors;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	public Proveedor getProveedorNuevo() {
		return proveedorNuevo;
	}

	public void setProveedorNuevo(Proveedor proveedorNuevo) {
		this.proveedorNuevo = proveedorNuevo;
	}

	public boolean isAccionEditar() {
		return accionEditar;
	}

	public void setAccionEditar(boolean accionEditar) {
		this.accionEditar = accionEditar;
	}
	
/* CUSTOM LABELS  */
	
	public String etiBotonDialog(){
		if(accionEditar){
			return "EDITAR";
		}else{
			return "NUEVO";
		}
	}
}
