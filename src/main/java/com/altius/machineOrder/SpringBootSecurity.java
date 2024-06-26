/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.altius.machineOrder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 *
 * @author altius
 */
@Configuration
@EnableWebSecurity
public class SpringBootSecurity {

    @Autowired
    private UserDetailsService userService;

    @Bean
    public PasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoProvider = new DaoAuthenticationProvider();
        daoProvider.setUserDetailsService(this.userService);
        daoProvider.setPasswordEncoder(this.bCryptPasswordEncoder());
        return daoProvider;
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.formLogin(f
                -> f
                        .loginPage("/login.htm")
                        .defaultSuccessUrl("/index.htm")
                        .permitAll()
        )
                .logout(out
                        -> out
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .logoutSuccessUrl("/login.htm")
                        .permitAll()
                )
                .authenticationProvider(this.authenticationProvider())
                .authorizeHttpRequests(r
                        -> r
                        .requestMatchers("/",
                                "/error/**",
                                "/js/**",
                                "/images/**",
                                "/css/**",
                                "/favicon.ico",
                                "/WEB-INF/jsp/**").permitAll()
                        .requestMatchers("/index.htm**").hasAnyAuthority("ROLE_BF_SHOW_HOME")
                        .requestMatchers(
                                "/addUser.htm**",
                                "/editUser.htm**",
                                "/showEditUser.htm**",
                                "/items.htm**",
                                "/level.htm**",
                                "/deleteOrder.htm**",
                                "/deleteMyOrder.htm**",
                                "/showEditItem.htm**",
                                "/editItem.htm**",
                                "/addItems.htm**"
                        ).hasAnyAuthority("ROLE_BF_MANAGE_USER")
                        .requestMatchers("/listUser.htm**",
                                "/listOrder.htm**",
                                "/itemOrder.htm**",
                                "/addOrder.htm**",
                                "/myOrder.htm**"
                        ).hasAnyAuthority("ROLE_BF_MANAGE_USER", "ROLE_BF_REGULAR_USER")
                );
        return http.build();
    }
}
