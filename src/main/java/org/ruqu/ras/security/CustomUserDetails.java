package org.ruqu.ras.security;

import org.ruqu.ras.domain.Rol;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.List;

public class CustomUserDetails extends User {


    private static final long serialVersionUID = 1L;

    public CustomUserDetails(String user, String clave, boolean enabled, boolean accountNonExpired
            , boolean credentialsNonExpired, boolean accountNonLocked,
                             Collection<GrantedAuthority> authorities, List<Rol> roles, int id, String email, String nombreCompleto) {
        super(user, clave, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.roles = roles;
        this.id = id;
        this.email = email;
        this.nombreCompleto = nombreCompleto;

    }

    private List<Rol> roles;
    private int id;
    private String email;
    private String nombreCompleto;
    private int idRol_activo = -1;

    public List<Rol> getRoles() {
        return roles;
    }

    public void setRoles(List<Rol> roles) {
        this.roles = roles;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public int getIdRol_activo() {
        return idRol_activo;
    }

    public void setIdRol_activo(int idRol_activo) {
        this.idRol_activo = idRol_activo;
    }

    public String getParentUsername() {
        return super.getUsername();
    }

    public String getParentPassword() {
        return super.getPassword();
    }

    public boolean getParentEnabled() {
        return super.isEnabled();
    }

    public boolean getParentAccountNotExpired() {
        return super.isAccountNonExpired();
    }

    public boolean getParentCredentialsNotExpired() {
        return super.isCredentialsNonExpired();
    }

    public boolean getParentAccountNotLocked() {
        return super.isAccountNonLocked();
    }

    public Collection<GrantedAuthority> getParentAuthorities() {
        return super.getAuthorities();
    }

    public static CustomUserDetails generarUsuario(CustomUserDetails user, Collection<GrantedAuthority> authorities) {
        return new CustomUserDetails(user.getParentUsername(), "", user.getParentEnabled(),
                user.getParentAccountNotExpired(), user.getParentCredentialsNotExpired(),
                user.getParentAccountNotLocked(), authorities, user.getRoles(), user.getId(),
                user.getEmail(), user.getNombreCompleto());
    }

}
