package org.ruqu.ras.beans;

import java.io.IOException;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Component;

@ManagedBean(name="loginBean")
@RequestScoped
public class LoginBean
{

    private String username = "";
    private String password = "";
    private boolean rememberMe = false;
    private boolean loggedIn = false;
    
    @PostConstruct
    public void initMyBean(){    
	     Map<String,String> requestParams = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
	     String get_id = requestParams.get("id");
	     if(get_id!=null){
	    	 int i = Integer.parseInt(get_id);
		     FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Informacion", "");
	    	 switch(i){
	    	 case 1:  message.setDetail("Ha salido del sistema de manera exitosa");break;
	    	 }
	    	 FacesContext.getCurrentInstance().addMessage(null, message);
	     }	     
    }
    
    
    public String doLogin() throws IOException, ServletException
    {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();

        RequestDispatcher dispatcher = ((ServletRequest) context.getRequest())
                .getRequestDispatcher("/j_spring_security_check");

        dispatcher.forward((ServletRequest) context.getRequest(), (ServletResponse) context.getResponse());

        FacesContext.getCurrentInstance().responseComplete();

        // It's OK to return null here because Faces is just going to exit.
        return null;

    }
    
    public void logout() throws IOException{
    	 ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
    	 ec.invalidateSession();
    	 ec.redirect(ec.getRequestContextPath() + "/pages/login.xhtml?id=1");
    }

    @PostConstruct
    private void handleErrorMessage()
    {
        Exception e = (Exception) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(
        		WebAttributes.AUTHENTICATION_EXCEPTION );

        if (e instanceof BadCredentialsException)
        {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(
            		WebAttributes.AUTHENTICATION_EXCEPTION, null);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Username or password not valid.", null));
        }
    }
    
    public String getUsername()
    {
        return this.username;
    }

    public void setUsername(final String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return this.password;
    }

    public void setPassword(final String password)
    {
        this.password = password;
    }

    public boolean isRememberMe()
    {
        return this.rememberMe;
    }

    public void setRememberMe(final boolean rememberMe)
    {
        this.rememberMe = rememberMe;
    }

    public boolean isLoggedIn()
    {
        return this.loggedIn;
    }

    public void setLoggedIn(final boolean loggedIn)
    {
        this.loggedIn = loggedIn;
    }
}
