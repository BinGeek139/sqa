package com.ptit.sqa.config;

import com.ptit.sqa.common.Const;
import com.ptit.sqa.config.jwt.CustomAccessDeniedHandler;
import com.ptit.sqa.config.jwt.JwtAuthenticationFilter;
import com.ptit.sqa.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {

        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .csrf()
                .disable();
        http.authorizeRequests().
                antMatchers("/login", "/403", "/error", "/sub-login",
                        "/resources/**",
                        "/static/**",
                        "/vendor/**",
                        "/js/**",
                        "/images/**",
                        "/fonts/**",
                        "/css/**",
                        "/bootstrap/**",
                        "/plugins/**",
                        "/webjars/**").
                permitAll().anyRequest().
                hasAnyRole(Const.ROLE_USER, Const.ROLE_TEACHER, Const.ROLE_ADMIN)
                .and().exceptionHandling()
                .accessDeniedHandler(new CustomAccessDeniedHandler());
    }

}

