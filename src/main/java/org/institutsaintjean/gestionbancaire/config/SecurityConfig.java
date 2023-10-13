package org.institutsaintjean.gestionbancaire.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {


    @Bean
    //authentication
    public UserDetailsService userDetailsService() {

        return new UtilisateurDetailsService(); //EtudiantDetailsService();//
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/", "/loginetud", "/loginuser", "/erreur404", "/css/**").permitAll()
                        .requestMatchers("/accueil").hasAnyAuthority("Etudiant", "Personnel", "Admin")
                        .requestMatchers("/listE", "/listU", "/saveE", "/saveU").hasAuthority("Admin")
                        .requestMatchers("/listI").hasAnyAuthority("Personnel", "Admin")
                        .requestMatchers("/saveI").hasAuthority("Etudiant")
                        .requestMatchers("/**").permitAll()
                        .anyRequest().authenticated()
                )
//                .formLogin((form) -> form
//                        .loginPage("/loginetud")
//                        .usernameParameter("matricule")
//                        .defaultSuccessUrl("/accueil")
//                        .failureUrl("/login-error1")
//                )
                .formLogin((form) -> form
                        .loginPage("/loginuser")
                        .usernameParameter("login")
                        .defaultSuccessUrl("/accueil")
                        .failureUrl("/login-error2")
                )
                .logout((logout) -> logout.permitAll())
                .exceptionHandling().accessDeniedPage("/access-denied");
        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new PasswordEncoderTest();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

}
