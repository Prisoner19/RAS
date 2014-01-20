package org.ruqu.ras.beans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;
import org.ruqu.ras.domain.Usuario;
import org.ruqu.ras.helpers.session.LoggedUserInfo;
import org.ruqu.ras.helpers.utils.FacesMessageHelper;
import org.ruqu.ras.security.PasswordUtils;
import org.ruqu.ras.service.IUsuarioService;

@ManagedBean(name="PerfilBean")
@ViewScoped
public class PerfilBean implements Serializable{
	
	/**
	 * Beans' attributes
	 * =================
	 */
	private static final String growlPath = "form:growl";
	private static final long serialVersionUID = 1L;
	
	
	@ManagedProperty(value="#{UsuarioService}")
	IUsuarioService usuarioService;
	
	Usuario user;
	
	private String previousPass;
	private String matchPass;
	
	public PerfilBean(){
		
	}
	
	@PostConstruct
	public void init(){
		user=usuarioService.getByUsuario(LoggedUserInfo.getDetails().getUsername());
	}
	
	public void cambiarPassword(){
		if( !PasswordUtils.comparar(previousPass, user.getPassword()) ){
			FacesMessageHelper.sendGrowlMessage(FacesMessage.SEVERITY_WARN, "Aviso", "La contraseña anterior no es correcta",growlPath);
			return;			
		}else{
			user.setPassword(PasswordUtils.encodePassword(matchPass));
			usuarioService.updateUsuario(user);
			previousPass="";
			matchPass="";
			FacesMessageHelper.sendGrowlMessage(FacesMessage.SEVERITY_INFO, "Aviso", "La nueva contraseña fue guardada con exito",growlPath);
			RequestContext.getCurrentInstance().update("form:pane_nuevaPass");
		}
	}

	public String getPreviousPass() {
		return previousPass;
	}

	public void setPreviousPass(String previousPass) {
		this.previousPass = previousPass;
	}

	public String getMatchPass() {
		return matchPass;
	}

	public void setMatchPass(String matchPass) {
		this.matchPass = matchPass;
	}

	public IUsuarioService getUsuarioService() {
		return usuarioService;
	}

	public void setUsuarioService(IUsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}
	

}
