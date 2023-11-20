package com.tpe.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

        // !!! Bu Class da amacimiz: AuthManager - Provider - PassEncoder  larimi olusturmak ve
        //  birbirleriyle tanistirmak

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable().
                authorizeHttpRequests().
                antMatchers("/","index.html","/css/*","/js/*","/register").permitAll().
                anyRequest().
                authenticated().
                and().
                httpBasic();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(10); // 4 ile 31 arasinda deger alabiliyor, deger yukseldikte
        // encoder kalitesi artiyor ama islem uzuyor
    }

    @Bean
    public DaoAuthenticationProvider authProvider(){
        // !!! userdetalservice - encoder ile tanistiriyorum
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setPasswordEncoder(passwordEncoder());
        authProvider.setUserDetailsService(userDetailsService);
        return authProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // !!! Provider ile tanistiriyorum
        auth.authenticationProvider(authProvider());
    }

}
