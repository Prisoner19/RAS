package org.ruqu.ras.security;

import com.google.common.collect.Lists;


import org.ruqu.ras.domain.Opcion;
import org.ruqu.ras.domain.Rol;
import org.ruqu.ras.helpers.utils.HttpRequestUtil;
import org.ruqu.ras.service.IRolService;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class MyFilterSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    IRolService rolService;

    private Map<String, List<ConfigAttribute>> cache;

    public MyFilterSecurityMetadataSource() {
        super();
        cache = new HashMap<String, List<ConfigAttribute>>();
    }

    public List<ConfigAttribute> getAttributes(Object object) {
        FilterInvocation fi = (FilterInvocation) object;
        String url = fi.getRequestUrl();

        String nombreVista = HttpRequestUtil.getOutComeFroUrl(url);

        List<String> listUrl = HttpRequestUtil.splitFolders(url);
        if (listUrl.size() > 0)
            listUrl = listUrl.subList(1, listUrl.size());
        listUrl = Lists.reverse(listUrl);

        if(cache.containsKey(nombreVista)){
        	
        	return cache.get(nombreVista);
        }else{
        	List<ConfigAttribute> lista_roles;
        	String[] roles=new String[0];
            if(nombreVista.compareTo(Opcion.WELCOME_VIEW)==0){
            	roles = new String[1];
            	roles[0] = Rol.HOME;
            	
            }else{
            	
            	Set<String> temp = new HashSet<String>();
            	for(String menu:listUrl){
            		List<String> list = rolService.getRolesMenuByRuta(menu);
            		if(list.size()>0){
            			temp.addAll(list);       		
            		}
            	}
            	if(temp.size()>0){
            		roles = temp.toArray(new String[0]);
            	}
            	
            }
            
            if(roles.length==0){
            	roles = new String[1];
            	roles[0] = Rol.NO_ROLS;
            }
          
           lista_roles= SecurityConfig.createList(roles);
           
           cache.put(nombreVista, lista_roles);

    	   return lista_roles;

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
