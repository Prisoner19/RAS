package org.ruqu.ras.service;

import org.ruqu.ras.dao.IRolDao;
import org.ruqu.ras.dao.IUsuarioDao;
import org.ruqu.ras.domain.Rol;
import org.ruqu.ras.domain.Usuario;
import org.ruqu.ras.security.CustomUserDetails;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class UserDetailsServiceImpl implements UserDetailsService {
    IUsuarioDao usuarioDao;

    IRolDao rolDao;

    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException, DataAccessException {

        CustomUserDetails user;
        int idRol_default = -1;
        System.out.println(username);
        Usuario us = usuarioDao.getByUsuario(username);

        if (us == null) {
            throw new UsernameNotFoundException("User not found");
        }

        List<Rol> roles = rolDao.getRolesUserbyId(us.getIdUsuario());
        System.out.println(roles);
        Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        if (roles.size() > 0) {
            authorities.add(new SimpleGrantedAuthority(roles.get(0).getDescripcion()));
            idRol_default = roles.get(0).getIdRol();
        }
        authorities.add(new SimpleGrantedAuthority(Rol.HOME));

        user = new CustomUserDetails(username, us.getPassword(), true,
                true, true, true, authorities, roles, us.getIdUsuario(),
                "", us.getLogin());
        user.setIdRol_activo(idRol_default);
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