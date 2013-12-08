package org.ruqu.ras.beans;

import java.io.Serializable;
import java.util.ArrayList;
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
import org.ruqu.ras.domain.Equipo;
import org.ruqu.ras.domain.Otrogasto;
import org.ruqu.ras.domain.Personalasignado;
import org.ruqu.ras.domain.Proyecto;
import org.ruqu.ras.helpers.utils.FacesMessageHelper;
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
	private static final String growlPath ="form:growl";
	
	@ManagedProperty(value="#{ProyectoService}")
	IProyectoService proyectoService;
	
	@ManagedProperty(value="#{PersonalasignadoService}")
	IPersonalasignadoService personalasignadoService;
	
	@ManagedProperty(value="#{EquipoService}")
	IEquipoService equipoService;
	
	@ManagedProperty(value="#{OtrogastoService}")
	IOtrogastoService otrogastoService;
	
	private List<Proyecto> proyectos;
	private List<Personalasignado> personalasignados;
	private List<Equipo> equipos;
	private List<Otrogasto> otrogastos;
	
	private Proyecto proyecto;
	private Proyecto proyectoSelec;
	
	private boolean accionEditar = false;

	
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
		proyecto=new Proyecto();
		proyectoSelec = new Proyecto();
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
		RequestContext.getCurrentInstance().execute("dialogPresupuesto.show();");
	}
	
	public void editarEvent(){
		if(proyectoSelec!=null){
			accionEditar=true;
			proyecto=proyectoService.getProyectoById(proyectoSelec.getIdProyecto());
			RequestContext.getCurrentInstance().execute("dialogPresupuesto.show();");
		}else
			FacesMessageHelper.sendGrowlMessage(FacesMessage.SEVERITY_WARN, 
					"Aviso", "No ha seleccionado ningun proyecto", growlPath);
	}
	
	public void gastoEvent(){
		if(proyectoSelec!=null){			
			proyecto=proyectoService.getProyectoById(proyectoSelec.getIdProyecto());
			RequestContext.getCurrentInstance().execute("dialogGastos.show();");
		}else
			FacesMessageHelper.sendGrowlMessage(FacesMessage.SEVERITY_WARN, 
					"Aviso", "No ha seleccionado ningun proyecto", growlPath);
	}	
	
	public void eliminarEvent(){
		if(proyectoSelec!=null){			
			proyectoService.deleteProyecto(proyectoSelec);
			refrescarProyectos();
			FacesMessageHelper.sendGrowlMessage(FacesMessage.SEVERITY_INFO, 
					"Aviso", "Se ha eliminado el Proyecto Seleccionado.", growlPath);
		}else
			FacesMessageHelper.sendGrowlMessage(FacesMessage.SEVERITY_WARN, 
					"Aviso", "No ha seleccionado ningun proyecto", growlPath);
	}
	
	public void onEdit(RowEditEvent event) {
		Proyecto c=(Proyecto)event.getObject();
		setProyecto(proyectoService.getProyectoById(c.getIdProyecto()));
		
		getProyecto().setNombre(c.getNombre());
		getProyecto().setDescripcion(c.getDescripcion());
		getProyecto().setInicio(c.getInicio());
		
		editar();
		
		FacesMessageHelper.sendGrowlMessage(FacesMessage.SEVERITY_INFO, "Proyecto Editado", ((Proyecto) event.getObject()).getNombre(), growlPath);

    }  
      
    public void onCancel(RowEditEvent event) {  
    	limpiarCampos();
    	FacesMessageHelper.sendGrowlMessage(FacesMessage.SEVERITY_INFO, "Edicion de Proyecto Cancelada.", ((Proyecto) event.getObject()).getNombre(), growlPath);
    }
    
	//BOTON PROCESAR-DIALOG
	public void procesarDialog(){
		if(accionEditar){
			editar();
			FacesMessageHelper.sendGrowlMessage(FacesMessage.SEVERITY_INFO, 
					"Aviso", "Presupuesto Estimado actualizado", growlPath);
			
		}else{
			insertar();
			FacesMessageHelper.sendGrowlMessage(FacesMessage.SEVERITY_INFO, 
					"Aviso", "Nuevo Proyecto Registrado", growlPath);
			
		}
		RequestContext.getCurrentInstance().execute("dialogPresupuesto.hide();");
		refrescarProyectos();
		
	}
	
	/* ACCIONES CRUD
	*  =============
	*/
	
	public void insertar(){
		getProyectoService().addProyecto(getProyecto());
	}
	
	public void editar(){
		//getProyecto().setIdProyecto(getProyecto().getIdProyecto());
		getProyectoService().updateProyecto(getProyecto());		
	}
	
	public void eliminar(){
		getProyectoService().deleteProyectoLogico(getProyectoSelec());		
	}
	

	/*
	*	RUTINAS ADICIONALES
	*	===================
    */


	public void limpiarCampos()
	{
		proyecto= new Proyecto();		
		proyectoSelec = new Proyecto();
	}

	private void refrescarProyectos()
	{
		setProyectos(getProyectoService().getProyectos());
		
	}
	
	public void timeZone(){
		TimeZone.getDefault();
	}
	
	public void showError(){
		FacesMessageHelper.sendGrowlMessage(FacesMessage.SEVERITY_WARN, "Registro","Campos resaltados invalidos", growlPath
				,"Error");		
	}
	

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

	public Proyecto getProyectoSelec() {
		return proyectoSelec;
	}

	public void setProyectoSelec(Proyecto proyectoNuevo) {
		this.proyectoSelec = proyectoNuevo;
	}

	public boolean isAccionEditar() {
		return accionEditar;
	}

	public void setAccionEditar(boolean accionEditar) {
		this.accionEditar = accionEditar;
	}
}
