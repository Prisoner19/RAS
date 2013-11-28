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
import org.ruqu.ras.domain.Rol;
import org.ruqu.ras.domain.Usuario;
import org.ruqu.ras.service.IRolService;
import org.ruqu.ras.service.IUsuarioService;

@ManagedBean(name="UsuarioBean")
@ViewScoped
public class UsuarioBean implements Serializable{

	/**
	 * Beans' attributes
	 * =================
	 */
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
		if(getUsuarioNuevo()!=null){
			RequestContext.getCurrentInstance().execute("confirmation.show()");
		}
		else{
			FacesMessage msg = null;  
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Seleccione un registro");  
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
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
		getUsuarioService().addUsuario(getUsuario());
		refrescarUsuarios();
		limpiarCampos();
	}
	
	public void editar(){
		//getUsuario().setIdUsuario(getUsuario().getIdUsuario());
		getUsuarioService().updateUsuario(getUsuario());
		refrescarUsuarios();
		limpiarCampos();
	}
	
	public void eliminar(){
		getUsuarioService().deleteUsuarioLogico(getUsuarioNuevo());
		refrescarUsuarios();
	}
	
	public void validarNuevo(){
		RequestContext context = RequestContext.getCurrentInstance();  
        FacesMessage msg = null;  
        boolean registrado = false;  
          
        if(getUsuario().getLogin()!=null && getUsuario().getLogin().length()>=1
        		&& getUsuario().getPassword()!=null && getUsuario().getPassword().length()>=1){
        	registrado=true;
        	msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registrado", getUsuario().getLogin());
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
        
        if(getUsuario().getLogin()!=null && getUsuario().getLogin().length()>=1
        		&& getUsuario().getPassword()!=null && getUsuario().getPassword().length()>=1){
        	editado=true;
        	msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Editado", getUsuario().getLogin());
            editar();
        }else if(getUsuarioNuevo()==null){
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
        
        if(getUsuarioNuevo()!=null){
        	eliminado = true;  
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminado", getUsuarioNuevo().getLogin());
            eliminar();
        } else {  
        	eliminado = false;  
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Seleccione un registro");  
        }  
          
        FacesContext.getCurrentInstance().addMessage(null, msg);  
        context.addCallbackParam("eliminado", eliminado);  
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

        System.out.println(c.getLogin());
        validarEditar();

        FacesMessage msg = new FacesMessage("Usuario Editado", ((Usuario) event.getObject()).getLogin());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Usuario Cancelado", ((Usuario) event.getObject()).getLogin());

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    
	/*
	*	RUTINAS ADICIONALES
	*	===================
    */


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
