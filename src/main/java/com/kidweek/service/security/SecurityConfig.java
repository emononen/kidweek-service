package com.kidweek.service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private FacebookAuthenticationProvider facebookAuthenticationProvider;
    @Autowired
    private FacebookAuthenticationFilter facebookAuthenticationFilter;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .antMatcher("/api/**").authorizeRequests().anyRequest().authenticated()
                .and()
                .addFilterBefore(facebookAuthenticationFilter, BasicAuthenticationFilter.class)

        ;
/*


        http.antMatcher("/api/**")
                .csrf().disable()
                .authorizeRequests()
                    .antMatchers("/api/**").authenticated()
                .anyRequest().permitAll()
                .and()
                .addFilterBefore(facebookAuthenticationFilter, BasicAuthenticationFilter.class);
*/
    }
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/v2/api-docs",
                "/configuration/**", "/swagger-resources/**",
                "/swagger-ui.html", "/webjars/**");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(facebookAuthenticationProvider);
    }

}
