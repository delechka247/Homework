package ru.itis.javalab.statementsservice.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import ru.itis.javalab.statementsservice.security.jwt.JwtAuthenticationFilter;
import ru.itis.javalab.statementsservice.security.jwt.JwtAuthenticationProvider;
import ru.itis.javalab.statementsservice.security.jwt.JwtLogoutFilter;
import ru.itis.javalab.statementsservice.security.jwt.RefreshTokenFilter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Autowired
    private RefreshTokenFilter refreshTokenFilter;

    @Autowired
    private JwtLogoutFilter jwtLogoutFilter;

    @Autowired
    private JwtAuthenticationProvider jwtAuthenticationProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.sessionManagement().disable();
        http
                .addFilterAt(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(refreshTokenFilter, JwtAuthenticationFilter.class)
                .addFilterAt(jwtLogoutFilter, LogoutFilter.class)
                .authorizeRequests()
                .antMatchers("/signUp").permitAll()
                .antMatchers("/signIn").permitAll()
                .antMatchers("/refresh").permitAll()
                .antMatchers("/profile").authenticated()
                .antMatchers("/logout").hasAnyAuthority()
                .and()
                .sessionManagement().disable();;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(jwtAuthenticationProvider);
    }

    @Bean
    public FilterRegistrationBean<JwtAuthenticationFilter> authAccessTokenFilter() {
        FilterRegistrationBean<JwtAuthenticationFilter> registrationBean
                = new FilterRegistrationBean<>();

        registrationBean.setFilter(new JwtAuthenticationFilter());
        registrationBean.addUrlPatterns("/**");

        return registrationBean;
    }
}

