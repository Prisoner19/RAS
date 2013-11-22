package org.ruqu.ras.security;

import org.ruqu.ras.domain.Rol;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.List;

public class CustomUserDetails extends User{

  
	private static final long serialVersionUID = 1L;

	public CustomUserDetails(String user, String clave,boolean enabled,boolean accountNonExpired
    		,boolean credentialsNonExpired,boolean accountNonLocked,
    		Collection<GrantedAuthority> authorities,List<Rol> roles){
         super(user,clave,enabled,accountNonExpired,credentialsNonExpired,accountNonLocked,authorities);
         this.roles=roles;
    }

    private List<Rol> roles;

	public List<Rol> getRoles() {
		return roles;
	}

	public void setRoles(List<Rol> roles) {
		this.roles = roles;
	}

   
    
}
