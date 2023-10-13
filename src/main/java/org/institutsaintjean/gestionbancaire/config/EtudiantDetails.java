package org.institutsaintjean.gestionbancaire.config;

import org.institutsaintjean.gestionbancaire.model.Etudiant;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class EtudiantDetails implements UserDetails {

    private String matricule;
    private String code_authentification;
    private List<GrantedAuthority> authorities;

    public EtudiantDetails(Etudiant etudiant) {
        matricule= etudiant.getMatricule();
        code_authentification= etudiant.getCode_authentification();
        authorities= Arrays.stream(etudiant.getRoles().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return code_authentification;
    }

    @Override
    public String getUsername() {
        return matricule;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
