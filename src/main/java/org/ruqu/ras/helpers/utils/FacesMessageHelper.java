package org.ruqu.ras.helpers.utils;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

public class FacesMessageHelper {
	public static void sendGrowlMessage(Severity s,String title,String message,String growlPath,String context){
		FacesMessage msg = new FacesMessage(s, title, 
				message);		
		FacesContext.getCurrentInstance().addMessage(context, msg); 
		
		RequestContext.getCurrentInstance().update(growlPath);
		
	}
	
	public static void sendGrowlMessage(Severity s,String title,String message,String growlPath){
		FacesMessage msg = new FacesMessage(s, title, 
				message);		
		FacesContext.getCurrentInstance().addMessage(null, msg); 
		
		RequestContext.getCurrentInstance().update(growlPath);
		
	}
	
	public static void sendGrowlMessage(Severity s,String title,String message){
		FacesMessage msg = new FacesMessage(s, title, 
				message);		
		FacesContext.getCurrentInstance().addMessage(null, msg); 

		
	}
}
