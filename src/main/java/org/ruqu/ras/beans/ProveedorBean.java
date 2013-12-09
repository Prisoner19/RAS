package org.ruqu.ras.beans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;
import org.ruqu.ras.domain.Distrito;
import org.ruqu.ras.domain.Proveedor;
import org.ruqu.ras.helpers.utils.FacesMessageHelper;
import org.ruqu.ras.service.IDistritoService;
import org.ruqu.ras.service.IProveedorService;

@ManagedBean(name="ProveedorBean")
@ViewScoped
public class ProveedorBean {

	/**
	 * Beans' attributes
	 * =================
	 */
	private static final long serialVersionUID = 1L;
	private static final String growlPath ="form:growl";
	
	@ManagedProperty(value="#{ProveedorService}")
	IProveedorService proveedorService;
	
	@ManagedProperty(value="#{DistritoService}")
	IDistritoService distritoService;
	
	List<Proveedor> proveedors;
	
	List<Distrito> distritos;
	
	Proveedor proveedor=new Proveedor();
	Proveedor proveedorNuevo=new Proveedor();
	
	private boolean accionEditar = true;


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

	public IDistritoService getDistritoService() {
		return distritoService;
	}

	public void setDistritoService(IDistritoService distritoService) {
		this.distritoService = distritoService;
	}

	public List<Proveedor> getProveedors() {
		return proveedors;
	}

	public void setProveedors(List<Proveedor> proveedors) {
		this.proveedors = proveedors;
	}

	public List<Distrito> getDistritos() {
		return distritos;
	}

	public void setDistritos(List<Distrito> distritos) {
		this.distritos = distritos;
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


 	/* CONSTRUCTOR AND POSTCONSTRUCT 
	*  ============================
 	*/
	
	public ProveedorBean(){
		proveedors=new ArrayList<Proveedor>();
	}
	
	@PostConstruct
	public void init(){
		proveedors=getProveedorService().getProveedors();
		distritos=getDistritoService().getDistritos();
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
				FacesMessage msg = null;  
				msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Seleccione un registro");  
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
		}catch(Exception e){
			System.out.println("ERROR: "+e.getMessage());
			accionEditar=false;
		}
	}
	
	public void eliminarEvent(){
		if(getProveedorNuevo()!=null){
			RequestContext.getCurrentInstance().execute("confirmation.show()");
		}
		else{
			FacesMessage msg = null;  
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Seleccione un registro");  
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}
	
	public void onEdit(RowEditEvent event) {
		Proveedor c=(Proveedor)event.getObject();
		setProveedor(proveedorService.getProveedorById(c.getIdProveedor()));
		
		getProveedor().setNombre(c.getNombre());
		getProveedor().setDireccion(c.getDireccion());
		getProveedor().setTelefono(c.getTelefono());
		getProveedor().setDistrito(distritoService.getDistritoById(c.getDistrito().getIdDistrito()));
		
		validarEditar();
		
        FacesMessage msg = new FacesMessage("Proveedor Editado", ((Proveedor) event.getObject()).getNombre());  
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }  
      
    public void onCancel(RowEditEvent event) {  
        FacesMessage msg = new FacesMessage("Proveedor Cancelado", ((Proveedor) event.getObject()).getNombre());  
  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    }
	//BOTON PROCESAR-DIALOG
	public void procesarDialog(){
		if(accionEditar){
			System.out.println("llegue");
			validarEditar();
		}else{
			validarNuevo();
		}
		RequestContext.getCurrentInstance().execute("dialog.hide();");
		refrescarProveedors();
	}
	
	/* ACCIONES CRUD
	*  =============
	*/
	
	public void insertar(){
		getProveedorService().addProveedor(getProveedor());
		refrescarProveedors();
		limpiarCampos();
	}
	
	public void editar(){
		//getProveedor().setIdProveedor(getProveedor().getIdProveedor());
		getProveedorService().updateProveedor(getProveedor());
		refrescarProveedors();
		limpiarCampos();
	}
	
	public void eliminar(){
		getProveedorService().deleteProveedorLogico(getProveedorNuevo());
		refrescarProveedors();
	}
	
	public void validarNuevo(){
		RequestContext context = RequestContext.getCurrentInstance();  
        FacesMessage msg = null;  
        boolean registrado = false;  
          
        if(getProveedor().getNombre()!=null && getProveedor().getNombre().length()>=1){
        	registrado=true;
        	msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registrado", getProveedor().getNombre());
            insertar();
        } else {  
        	registrado = false;  
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error de registro", "Campo(s) invalido(s)");  
        }
        FacesContext.getCurrentInstance().addMessage(null, msg);  
        context.addCallbackParam("registrado", registrado);
	}
	
	public void validarEditar(){
		RequestContext context = RequestContext.getCurrentInstance();  
        FacesMessage msg = null;  
        boolean editado = false;  
        
        if(getProveedor().getNombre()!=null && getProveedor().getNombre().length()>=1){
        	editado=true;
        	msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Editado", getProveedor().getNombre());
            editar();
        }else if(getProveedorNuevo()==null){
        	editado = false;  
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Seleccione un registro");  
        }else{
        	editado = false;  
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Nombre invalido");  
        }
        FacesContext.getCurrentInstance().addMessage(null, msg);  
        context.addCallbackParam("editado", editado);  
	}
	
	public void validarEliminar(){
		RequestContext context = RequestContext.getCurrentInstance();  
        FacesMessage msg = null;  
        boolean eliminado = false;  
        
        if(getProveedorNuevo()!=null){
        	eliminado = true;  
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminado", getProveedorNuevo().getNombre());
            eliminar();
        } else {  
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
		proveedor= new Proveedor();		
		proveedorNuevo = new Proveedor();
	}

	private void refrescarProveedors()
	{
		setProveedors(getProveedorService().getProveedors());
		
	}
}
