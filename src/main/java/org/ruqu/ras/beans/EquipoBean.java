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
import org.primefaces.event.TabChangeEvent;
import org.ruqu.ras.domain.Categoria;
import org.ruqu.ras.domain.Equipo;
import org.ruqu.ras.helpers.utils.FacesMessageHelper;
import org.ruqu.ras.service.ICategoriaService;
import org.ruqu.ras.service.IEquipoService;

@ManagedBean(name="EquipoBean")
@ViewScoped
public class EquipoBean implements Serializable{

	private static final long serialVersionUID = 1L;
	private static final String growlPath ="form:growl";
	
	@ManagedProperty(value="#{EquipoService}")
	IEquipoService equipoService;
	
	@ManagedProperty(value = "#{CategoriaService}")
	ICategoriaService categoriaService; 
	
	List<Equipo> equipos;
	
	List<Categoria> categorias;
	
	Equipo equipo=new Equipo();
	Equipo equipoNuevo=new Equipo();
	String nombreCategoria;
	
	private boolean accionEditar = true;


	/* SETTER AND GETTERS
	*  ==================
	*/
	
	public IEquipoService getEquipoService() {
		return equipoService;
	}

	public void setEquipoService(IEquipoService equipoService) {
		this.equipoService = equipoService;
	}

	public List<Equipo> getEquipos() {
		return equipos;
	}

	public void setEquipos(List<Equipo> equipos) {
		this.equipos = equipos;
	}

	public Equipo getEquipo() {
		return equipo;
	}

	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}

	public Equipo getEquipoNuevo() {
		return equipoNuevo;
	}

	public void setEquipoNuevo(Equipo equipoNuevo) {
		this.equipoNuevo = equipoNuevo;
	}

	public boolean isAccionEditar() {
		return accionEditar;
	}

	public void setAccionEditar(boolean accionEditar) {
		this.accionEditar = accionEditar;
	}
	
	public ICategoriaService getCategoriaService() {
		return categoriaService;
	}

	public void setCategoriaService(ICategoriaService categoriaService) {
		this.categoriaService = categoriaService;
	}
	
	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}
	
	public void setNombreCategoria(String nombreCategoria){
		this.nombreCategoria = nombreCategoria;
	}
	
	public String getNombreCategoria(){
		return nombreCategoria;
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
	
	public EquipoBean(){
		equipos=new ArrayList<Equipo>();
	}
	
	@PostConstruct
	public void init(){
		equipos=getEquipoService().getEquipos();
		categorias=getCategoriaService().getCategorias();
		//System.out.print("\n\n"+equipos.size());
		System.out.print("\n\n"+categorias.size());
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
			if(getEquipoNuevo()!=null){
				setEquipo(getEquipoService().getEquipoById(getEquipoNuevo().getIdEquipo()));
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
		if(getEquipoNuevo()!=null){
			RequestContext.getCurrentInstance().execute("confirmation.show()");
		}
		else{
			FacesMessage msg = null;  
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Seleccione un registro");  
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}

	
	public void onEdit(RowEditEvent event) {
		Equipo e=(Equipo)event.getObject();
		setEquipo(equipoService.getEquipoById(e.getIdEquipo()));
		
		getEquipo().setNombre(e.getNombre());
		getEquipo().setCategoria(categoriaService.getCategoriaById(e.getCategoria().getIdCategoria()));
		getEquipo().setCodigo(e.getCodigo());
		getEquipo().setDescripcion(e.getDescripcion());
		getEquipo().setCosto(e.getCosto());
		getEquipo().setStock(e.getStock());
		
		System.out.println(getEquipo().getCategoria().getNombre());
		validarEditar();
    }  
      
    public void onCancel(RowEditEvent event) {  
        FacesMessage msg = new FacesMessage("Edición Cancelada", ((Equipo) event.getObject()).getNombre());  
  
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
		refrescarEquipos();
	}
	
	/* ACCIONES CRUD
	*  =============
	*/
	
	public void insertar(){
		getEquipoService().addEquipo(getEquipo());
		refrescarEquipos();
		refrescarCategorias();
		limpiarCampos();
	}
	
	public void editar(){
		//getEquipo().setIdEquipo(getEquipo().getIdEquipo());
		getEquipoService().updateEquipo(getEquipo());
		refrescarEquipos();
		refrescarCategorias();
		limpiarCampos();
	}
	
	public void eliminar(){
		getEquipoService().deleteEquipoLogico(getEquipoNuevo());
		refrescarEquipos();
	}
	
	public void validarNuevo(){
		RequestContext context = RequestContext.getCurrentInstance();  
        FacesMessage msg = null;  
        boolean registrado = false;  

        if(getEquipo().getCodigo()!=null){
        	
        	Categoria c = buscarCategoriaListaXNombre(nombreCategoria);
        	if(c == null){
        		c = new Categoria();
        		c.setNombre(nombreCategoria);
        		categoriaService.addCategoria(c);
        	}
        	getEquipo().setCategoria(c);
        	
        	registrado=true;
        	msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "EQUIPO REGISTRADO", getEquipo().getNombre());
            insertar();
        } else {  
        	registrado = false;  
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error de registro", "Campo(s) invalido(s)");  
        }
        System.out.println("\n\n"+getEquipo().getCategoria()+"\n");
        FacesContext.getCurrentInstance().addMessage(null, msg);  
        context.addCallbackParam("registrado", registrado);
        refrescarCategorias();
	}
	
	public void validarEditar(){
		RequestContext context = RequestContext.getCurrentInstance();  
        FacesMessage msg = null;  
        boolean editado = false;  
        
        if(getEquipo().getCodigo()!=null && getEquipo().getCodigo().length()>=1){
        	editado=true;
        	msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "EQUIPO EDITADO", getEquipo().getNombre());
            editar();
        }else if(getEquipoNuevo()==null){
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
        
        if(getEquipoNuevo()!=null){
        	eliminado = true;  
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "EQUIPO ELIMINADO", getEquipoNuevo().getNombre());
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
		equipo= new Equipo();		
		equipoNuevo = new Equipo();
		nombreCategoria = null;
	}

	private void refrescarEquipos()
	{
		setEquipos(getEquipoService().getEquipos());
	}
	
	private void refrescarCategorias(){
		setCategorias(categoriaService.getCategorias());
	}
	
	public List<String> listarCategorias(){
		List<String> results = new ArrayList<String>();
		
		for (Categoria cat : categorias) {
			results.add(cat.getNombre());
		}
		
		return results;
	}
	
	public Categoria buscarCategoriaListaXNombre(String catString){
		
		Categoria objCategoria = null;
		
		for(Categoria cat: categorias){
			if(cat.getNombre().equalsIgnoreCase(catString) == true){
				objCategoria = cat;
			}
		}
		
		return objCategoria;
	}
}
