package com.rayanen.banking.config;

import com.rayanen.banking.filter.SimpleCORSFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SimpleCORSFilter simpleCORSFilter;

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("credit").password(passwordEncoder().encode("1")).roles("CREDITS_OPERATOR")
        .and()
        .withUser("manager").password(passwordEncoder().encode("1")).roles("BRANCH_MANAGER")
        .and()
         .withUser("cashier").password(passwordEncoder().encode("1")).roles("CASH_DESK")
        ;
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.headers().frameOptions().disable().and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/money-bag.png").permitAll()
                .antMatchers(HttpMethod.GET, "/ws/login").permitAll()
//                .hasAnyRole("CREDITS_OPERATOR","BRANCH_MANAGER","CASH_DESK")
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .antMatchers(HttpMethod.POST, "/ws/**").permitAll()
//                .hasAnyRole("CREDITS_OPERATOR","BRANCH_MANAGER","CASH_DESK")
                .antMatchers("/pws/**").permitAll()
//                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/ws/login")
                .usernameParameter("user").passwordParameter("pass")
                .failureUrl("/pws/error?code=loginFailure")
                .defaultSuccessUrl("/ws/login")
                .and()
                .logout()
                .logoutUrl("/ws/logout")
                .deleteCookies("JSESSIONID");

        http.addFilterBefore(simpleCORSFilter, org.springframework.security.web.context.SecurityContextPersistenceFilter.class);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
