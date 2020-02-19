package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebConfig extends WebSecurityConfigurerAdapter {

    // @Autowired
    // private MyBasicAuthenticationEntryPoint authenticationEntryPoint;

    // @Bean
    // protected UserDetailsService userDetailsService() {
    //     // TODO Auto-generated method stub
    //     return new UserDetailsServiceImp();
    // }


    @Bean
    public PasswordEncoder passwordEncoder (){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
            .withUser("Customer").password(passwordEncoder().encode("customer"))
            .authorities("ROLE_USER")
            .and()
            .withUser("Manager").password(passwordEncoder().encode("manager"))
            .authorities("ROLE_MANAGER")
            .and()
            .withUser("admin").password(passwordEncoder().encode("admin"))
            .authorities("ROLE_ADMIN");
        // auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers("/api/v1/customers").permitAll()
                .and().httpBasic()
                .and()
                // .authenticationEntryPoint(authenticationEntryPoint)
                // .and()
                .headers().frameOptions().disable();
    }
    
}