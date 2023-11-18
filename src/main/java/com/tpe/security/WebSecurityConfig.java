package com.tpe.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) // method seviyesinde yetkilendirme için yaptık
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    // Bu class da amacımız: AuthManager - Provider- PassEncoder larimi oluşturmak

}
