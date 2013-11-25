package org.ruqu.ras.helpers.session;

import org.ruqu.ras.domain.Rol;
import org.ruqu.ras.security.CustomUserDetails;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collection;

public class LoggedUserInfo {
    public static CustomUserDetails getDetails() {
        CustomUserDetails user = (CustomUserDetails) SecurityContextHolder.getContext().
                getAuthentication().getPrincipal();

        return user;
    }

    public static boolean isThereASession() {
        return SecurityContextHolder.getContext().
                getAuthentication() == null ? false : true;
    }

    public static void changeAuthorities(Collection<GrantedAuthority> list, Rol rolSelected) {
        Authentication oldAuth = SecurityContextHolder.getContext().
                getAuthentication();

        CustomUserDetails user = CustomUserDetails.generarUsuario(getDetails(), list);
        if (rolSelected != null) {
            user.setIdRol_activo(rolSelected.getIdRol());
        }
        Authentication newAuth =
                new UsernamePasswordAuthenticationToken(user
                        , oldAuth.getCredentials(), list);

        SecurityContextHolder.getContext().setAuthentication(newAuth);
    }
}
