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
import org.ruqu.ras.domain.Rol;
import org.ruqu.ras.domain.Usuario;
import org.ruqu.ras.helpers.utils.FacesMessageHelper;
import org.ruqu.ras.security.PasswordUtils;
import org.ruqu.ras.service.IRolService;
import org.ruqu.ras.service.IUsuarioService;

@ManagedBean(name="UsuarioBean")
@ViewScoped
public class UsuarioBean implements Serializable{

	/**
	 * Beans' attributes
	 * =================
	 */
	private static final String growlPath = "form:growl";
	private static final long serialVersionUID = 1L;
	
	@ManagedProperty(value="#{UsuarioService}")
	IUsuarioService usuarioService;

    @ManagedProperty(value="#{RolService}")
    IRolService rolService;
	
	private List<Usuario> usuarios;
    private List<Rol> roles;

	private Usuario usuario;
	private Usuario usuarioNuevo;
	
	private boolean accionEditar = true;



 	/* CONSTRUCTOR AND POSTCONSTRUCT 
	*  ============================
 	*/
	
	public UsuarioBean(){
		usuarios=new ArrayList<Usuario>();
        usuario = new Usuario();
        usuarioNuevo = new Usuario();
	}
	
	@PostConstruct
	public void init(){
		usuarios=getUsuarioService().getUsuarios();
        roles = rolService.getRols();
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
			if(getUsuarioNuevo()!=null){
				setUsuario(getUsuarioService().getUsuarioById(getUsuarioNuevo().getIdUsuario()));
				RequestContext.getCurrentInstance().execute("dialog.show()");
			}
			else{
				FacesMessageHelper.sendGrowlMessage(FacesMessage.SEVERITY_WARN, "Error", "Seleccione un registro", growlPath);
			}
		}catch(Exception e){
			System.out.println("ERROR: "+e.getMessage());
			accionEditar=false;
		}
	}
	
	public void eliminarEvent(){
		if(getUsuarioNuevo()!=null){
			RequestContext.getCurrentInstance().execute("confirmation.show()");
		}
		else{			
			FacesMessageHelper.sendGrowlMessage(FacesMessage.SEVERITY_WARN, "Error", "Seleccione un registro", growlPath);
		}
	}
	
	//BOTON PROCESAR-DIALOG
	public void procesarDialog(){
		if(accionEditar){			
			editar();
		}else{
			insertar();
		}
		limpiarCampos();
		refrescarUsuarios();
		RequestContext.getCurrentInstance().execute("dialog.hide();");
	}
	
	/* ACCIONES CRUD
	*  =============
	*/
	
	public void insertar(){
		getUsuario().setPassword(PasswordUtils.encodePassword(getUsuario().getPassword()));
		getUsuarioService().addUsuario(getUsuario());		
	}
	
	public void editar(){
		
		getUsuario().setPassword(PasswordUtils.encodePassword(getUsuario().getPassword()));
		getUsuarioService().updateUsuario(getUsuario());	
	}
	
	public void eliminar(){
		getUsuarioService().deleteUsuarioLogico(getUsuarioNuevo());		
	}
	
		
	public void validarEliminar(){
		
        
        if(getUsuarioNuevo()!=null){
        	FacesMessageHelper.sendGrowlMessage(FacesMessage.SEVERITY_INFO,"Eliminado", getUsuarioNuevo().getLogin(), growlPath);
        	eliminar();
        } else {  
        	FacesMessageHelper.sendGrowlMessage(FacesMessage.SEVERITY_WARN, "Error", "Seleccione un registro", growlPath);
        }  
        
        limpiarCampos();
        refrescarUsuarios();      
      
	}

    /*
	*	ON CELL EDIT
	*	===================
    */
    
    public void onEdit(RowEditEvent event) {
        Usuario c=(Usuario)event.getObject();
        setUsuario(usuarioService.getUsuarioById(c.getIdUsuario()));

        getUsuario().setLogin(c.getLogin());
        getUsuario().setPassword(c.getPassword());        
        editar();
        FacesMessageHelper.sendGrowlMessage(FacesMessage.SEVERITY_INFO, "Usuario Editado", ((Usuario) event.getObject()).getLogin(), growlPath);

    }

    public void onCancel(RowEditEvent event) {
    	 FacesMessageHelper.sendGrowlMessage(FacesMessage.SEVERITY_INFO, "Edicion Cancelado", ((Usuario) event.getObject()).getLogin(), growlPath);
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
		usuario= new Usuario();		
		usuarioNuevo = new Usuario();
	}

	private void refrescarUsuarios()
	{
		setUsuarios(getUsuarioService().getUsuarios());
		
	}

    /* CUSTOM LABELS  */

    public String etiBotonDialog(){
        if(accionEditar){
            return "Editar";
        }else{
            return "Nuevo";
        }
    }

    /* SETTER AND GETTERS
	*  ==================
	*/

    public IRolService getRolService() {
        return rolService;
    }

    public void setRolService(IRolService rolService) {
        this.rolService = rolService;
    }

    public List<Rol> getRoles() {
        return roles;
    }

    public void setRoles(List<Rol> roles) {
        this.roles = roles;
    }

    public IUsuarioService getUsuarioService() {
        return usuarioService;
    }

    public void setUsuarioService(IUsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getUsuarioNuevo() {
        return usuarioNuevo;
    }

    public void setUsuarioNuevo(Usuario usuarioNuevo) {
        this.usuarioNuevo = usuarioNuevo;
    }

    public boolean isAccionEditar() {
        return accionEditar;
    }

    public void setAccionEditar(boolean accionEditar) {
        this.accionEditar = accionEditar;
    }
}
