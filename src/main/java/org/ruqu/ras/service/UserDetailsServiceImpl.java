package org.ruqu.ras.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;    
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.ruqu.ras.dao.IRolDao;
import org.ruqu.ras.dao.IUsuarioDao;
import org.ruqu.ras.domain.Rol;
import org.ruqu.ras.domain.Usuario;
import org.ruqu.ras.security.CustomUserDetails;


public class UserDetailsServiceImpl implements UserDetailsService {
	
	

  IUsuarioDao usuarioDao;
  

  IRolDao rolDao;

  @Transactional(readOnly = true)
  public UserDetails loadUserByUsername(String username)
      throws UsernameNotFoundException, DataAccessException {
	  
	  	CustomUserDetails user;			
	    	
	  	
	  	Usuario us = usuarioDao.getByUsuario(username);

        if(us==null){
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
	  	List<Rol> roles = rolDao.getRolesUserbyId(us.getIdUsuario());

        System.out.println("Roles-Usuario: "+roles)  ;
	  	
	  	Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
	  	
	  	for(Rol r:roles){
	  		authorities.add(new SimpleGrantedAuthority(r.getDescripcion()));
	  	}  	
	  	
	  	authorities.add(new SimpleGrantedAuthority("ROLE_HOME"));
	  	
	    user = new CustomUserDetails(username, us.getPassword(), true,
		      true, true, true, authorities,roles);			
	  	 
		return user;
  }

    public IUsuarioDao getUsuarioDao() {
        return usuarioDao;
    }

    public void setUsuarioDao(IUsuarioDao usuarioDao) {
        this.usuarioDao = usuarioDao;
    }

    public IRolDao getRolDao() {
        return rolDao;
    }

    public void setRolDao(IRolDao rolDao) {
        this.rolDao = rolDao;
    }
}