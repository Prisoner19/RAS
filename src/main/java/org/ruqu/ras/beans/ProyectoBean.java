package org.ruqu.ras.beans;

import java.io.Serializable;
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
import org.ruqu.ras.domain.Equipo;
import org.ruqu.ras.domain.Otrogasto;
import org.ruqu.ras.domain.Personalasignado;
import org.ruqu.ras.domain.Proyecto;
import org.ruqu.ras.service.IEquipoService;
import org.ruqu.ras.service.IOtrogastoService;
import org.ruqu.ras.service.IPersonalasignadoService;
import org.ruqu.ras.service.IProyectoService;

@ManagedBean (name="ProyectoBean")
@ViewScoped
public class ProyectoBean implements Serializable{

	/**
	 * Beans' attributes
	 * =================
	 */
	private static final long serialVersionUID = 1L;
	
	@ManagedProperty(value="#{ProyectoService}")
	IProyectoService proyectoService;
	
	@ManagedProperty(value="#{PersonalasignadoService}")
	IPersonalasignadoService personalasignadoService;
	
	@ManagedProperty(value="#{EquipoService}")
	IEquipoService equipoService;
	
	@ManagedProperty(value="#{OtrogastoService}")
	IOtrogastoService otrogastoService;
	
	List<Proyecto> proyectos;
	List<Personalasignado> personalasignados;
	List<Equipo> equipos;
	List<Otrogasto> otrogastos;
	
	Proyecto proyecto=new Proyecto();
	Proyecto proyectoNuevo=new Proyecto();
	
	private boolean accionEditar = false;


	/* SETTER AND GETTERS
	*  ==================
	*/
	
	public IProyectoService getProyectoService() {
		return proyectoService;
	}

	public IPersonalasignadoService getPersonalasignadoService() {
		return personalasignadoService;
	}

	public void setPersonalasignadoService(
			IPersonalasignadoService personalasignadoService) {
		this.personalasignadoService = personalasignadoService;
	}

	public IEquipoService getEquipoService() {
		return equipoService;
	}

	public void setEquipoService(IEquipoService equipoService) {
		this.equipoService = equipoService;
	}

	public IOtrogastoService getOtrogastoService() {
		return otrogastoService;
	}

	public void setOtrogastoService(IOtrogastoService otrogastoService) {
		this.otrogastoService = otrogastoService;
	}

	public List<Personalasignado> getPersonalasignados() {
		return personalasignados;
	}

	public void setPersonalasignados(List<Personalasignado> personalasignados) {
		this.personalasignados = personalasignados;
	}

	public List<Equipo> getEquipos() {
		return equipos;
	}

	public void setEquipos(List<Equipo> equipos) {
		this.equipos = equipos;
	}

	public List<Otrogasto> getOtrogastos() {
		return otrogastos;
	}

	public void setOtrogastos(List<Otrogasto> otrogastos) {
		this.otrogastos = otrogastos;
	}

	public void setProyectoService(IProyectoService proyectoService) {
		this.proyectoService = proyectoService;
	}

	public List<Proyecto> getProyectos() {
		return proyectos;
	}

	public void setProyectos(List<Proyecto> proyectos) {
		this.proyectos = proyectos;
	}

	public Proyecto getProyecto() {
		return proyecto;
	}

	public void setProyecto(Proyecto proyecto) {
		this.proyecto = proyecto;
	}

	public Proyecto getProyectoNuevo() {
		return proyectoNuevo;
	}

	public void setProyectoNuevo(Proyecto proyectoNuevo) {
		this.proyectoNuevo = proyectoNuevo;
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
			return "Editar";
		}else{
			return "Nuevo";
		}
	}


 	/* CONSTRUCTOR AND POSTCONSTRUCT 
	*  ============================
 	*/
	
	public ProyectoBean(){
		proyectos=new ArrayList<Proyecto>();
	}
	
	@PostConstruct
	public void init(){
		proyectos=getProyectoService().getProyectos();
	}
	
	/* AJAX BUTTON EVENTS  
	*  ==================
	*/

	public void toggle(String a){
		int num = Integer.parseInt(a);
		
		Proyecto p=proyectos.get(num);
		proyectoService.updateProyecto(p);
	}
	
	public void nuevoEvent(){
		accionEditar=false;
		limpiarCampos();
	}
	
	public void editarEvent(){
		accionEditar=true;
		try{
			if(getProyectoNuevo()!=null){
				setProyecto(getProyectoService().getProyectoById(getProyectoNuevo().getIdProyecto()));
				RequestContext.getCurrentInstance().execute("dialogPresupuesto.show()");
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
		if(getProyectoNuevo()!=null){
			RequestContext.getCurrentInstance().execute("confirmation.show()");
		}
		else{
			FacesMessage msg = null;  
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Seleccione un registro");  
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}
	
	public void onEdit(RowEditEvent event) {
		Proyecto c=(Proyecto)event.getObject();
		setProyecto(proyectoService.getProyectoById(c.getIdProyecto()));
		
		getProyecto().setNombre(c.getNombre());
		getProyecto().setDescripcion(c.getDescripcion());
		getProyecto().setInicio(c.getInicio());
		
		validarEditar();
		
        FacesMessage msg = new FacesMessage("Proyecto Editado", ((Proyecto) event.getObject()).getNombre());  
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }  
      
    public void onCancel(RowEditEvent event) {  
        FacesMessage msg = new FacesMessage("Proyecto Cancelado", ((Proyecto) event.getObject()).getNombre());  
  
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
	}
	
	/* ACCIONES CRUD
	*  =============
	*/
	
	public void insertar(){
		getProyectoService().addProyecto(getProyecto());
		refrescarProyectos();
		limpiarCampos();
	}
	
	public void editar(){
		//getProyecto().setIdProyecto(getProyecto().getIdProyecto());
		getProyectoService().updateProyecto(getProyecto());
		refrescarProyectos();
		limpiarCampos();
	}
	
	public void eliminar(){
		getProyectoService().deleteProyectoLogico(getProyectoNuevo());
		refrescarProyectos();
	}
	
	public void validarNuevo(){
		RequestContext context = RequestContext.getCurrentInstance();  
        FacesMessage msg = null;  
        boolean registrado = false;  
          
        if(getProyecto().getNombre()!=null && getProyecto().getNombre().length()>=1){
        	registrado=true;
        	msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registrado", getProyecto().getNombre());
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
        
        if(getProyecto().getNombre()!=null && getProyecto().getNombre().length()>=1){
        	editado=true;
        	msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Editado", getProyecto().getNombre());
            editar();
        }else if(getProyectoNuevo()==null){
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
        
        if(getProyectoNuevo()!=null){
        	eliminado = true;  
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminado", getProyectoNuevo().getNombre());
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


	public void limpiarCampos()
	{
		proyecto= new Proyecto();		
		proyectoNuevo = new Proyecto();
	}

	private void refrescarProyectos()
	{
		setProyectos(getProyectoService().getProyectos());
		
	}
}
