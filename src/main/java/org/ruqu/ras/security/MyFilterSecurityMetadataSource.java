package org.ruqu.ras.security;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.apache.commons.io.FilenameUtils;

import org.ruqu.ras.service.IRolService;


public class MyFilterSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

	IRolService rolService;
	
	private Map <String, List<ConfigAttribute>> cache;
	
	public MyFilterSecurityMetadataSource(){
		super();
		cache= new HashMap<String, List<ConfigAttribute>>();
	}
	
	public List<ConfigAttribute> getAttributes(Object object) {
    	FilterInvocation fi = (FilterInvocation) object;
        String url = fi.getRequestUrl();
        // String httpMethod = fi.getRequest().getMethod();
        //List<ConfigAttribute> attributes = new ArrayList<ConfigAttribute>();               
       
        System.out.println(url);

        String newUrl = FilenameUtils.getBaseName(url.replaceAll(".*/",""));
       
        newUrl= newUrl.replaceAll("^[^-]*-", "");
        
        if(cache.containsKey(newUrl)){

        	return cache.get(newUrl);
        }else{
        	List<ConfigAttribute> list;
        	String[] roles=new String[0];
            if(newUrl.compareTo("Inicio")==0){
            	roles = new String[1];
            	roles[0] = "ROLE_HOME";
            	
            }else{
            	try{
                    roles = rolService.getRolesMenuByRuta(newUrl).toArray(new String[0]);
                    System.out.println("Roles-Vista: "+roles)  ;
                } catch (Exception e){
                    System.out.println(e.getMessage())  ;
                }
            	
            }
          
            list= SecurityConfig.createList(roles);
           
            cache.put(newUrl, list);

    	   return list;
        	
        	
        }

    }

    public Collection<ConfigAttribute> getAllConfigAttributes() {
      return null;
    }

    public boolean supports(Class<?> clazz) {
      return FilterInvocation.class.isAssignableFrom(clazz);
    }

    public IRolService getRolService() {
        return rolService;
    }

    public void setRolService(IRolService rolService) {
        this.rolService = rolService;
    }
}
