package com.ocean.backend.config;

import com.ocean.backend.security.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomUserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(PasswordEncoder encoder) {
        var provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(encoder);
        return provider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, DaoAuthenticationProvider provider) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authenticationProvider(provider)
            .requestCache(cache -> cache.disable())

            .authorizeHttpRequests(auth -> auth
                // ===== Static resources (Spring Boot common locations) =====
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()

                // ===== Your static folders =====
                // src/main/resources/static/site/**      -> /site/**
                // src/main/resources/static/adminAssets/** -> /adminAssets/**
                .requestMatchers(
                    "/site/**",
                    "/adminAssets/**",
                    "/favicon.ico"
                ).permitAll()

                // ===== Auth pages =====
                .requestMatchers("/admin/login").permitAll()

                // ===== Public pages =====
                .requestMatchers("/", "/site/**").permitAll()

                // ===== Admin area =====
                .requestMatchers("/admin/**").hasAnyRole("ADMIN", "EDITOR")

                // ===== Everything else =====
                .anyRequest().permitAll()
            )

            .formLogin(form -> form
                .loginPage("/admin/login")
                .loginProcessingUrl("/admin/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/admin", true)
                .failureUrl("/admin/login?error=true")
                .permitAll()
            )

            .logout(logout -> logout
                .logoutUrl("/admin/logout")
                .logoutSuccessUrl("/admin/login?logout=true")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
            );

        return http.build();
    }
}
