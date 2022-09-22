package com.devmountain.ingresosegresos.security;

import com.devmountain.ingresosegresos.handler.CustomSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Autowired
    CustomSuccessHandler customSuccessHandler;

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception{
        auth.jdbcAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .dataSource(dataSource)
                .usersByUsernameQuery("select email,password,estado from empleados where email=?")
                .authoritiesByUsernameQuery("select email, rol from empleados where email=?");
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/","verEmpresas/**").hasRole("ADMIN")
                .antMatchers("/verEmpleados/**").hasRole("ADMIN")
                .antMatchers("/empresa/**").hasRole("ADMIN")
                .antMatchers("/empleado/**").hasRole("ADMIN")
                .antMatchers("/verMovimientos/**").hasAnyRole("ADMIN","OPERARIO")
                .antMatchers("/agregarMovimiento/**").hasAnyRole("ADMIN","OPERARIO")
                .antMatchers("/editarMovimiento/**").hasAnyRole("ADMIN","OPERARIO")
                .and().formLogin().successHandler(customSuccessHandler)
                .and().exceptionHandling().accessDeniedPage("/Denegado")
                .and().logout().permitAll();
    }
}
