package com.ocean.backend.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.ocean.backend.security.CustomUserDetailsService;

import lombok.RequiredArgsConstructor;

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
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            // Tắt CSRF nếu bạn không dùng (Tuy nhiên nếu làm web form bình thường, cân nhắc bật lại bảo mật)
            .csrf(AbstractHttpConfigurer::disable)
            
            // Tắt Request Cache (Không lưu lại request cũ bắt người dùng phải quay lại trang trước khi login)
            .requestCache(AbstractHttpConfigurer::disable)
            
            // Set Authentication Provider
            .authenticationProvider(authenticationProvider())

            // ===== CẤU HÌNH PHÂN QUYỀN URL =====
            .authorizeHttpRequests(auth -> auth
                // 1. Tài nguyên tĩnh (CSS, JS, Images, v.v.)
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                .requestMatchers("/site/**", "/adminAssets/**", "/favicon.ico").permitAll()

                // 2. Các trang công khai (Trang chủ, Login, Error)
                .requestMatchers("/", "/admin/login", "/error").permitAll()

                // 3. Khu vực Admin yêu cầu quyền (Role)
                // Lưu ý: Trong DB/UserDetailsService, role phải có tiền tố "ROLE_", ví dụ: "ROLE_ADMIN"
                .requestMatchers("/admin/**").hasAnyRole("ADMIN", "EDITOR")

                // 4. Các request còn lại (Public hết theo ý bạn ban đầu)
                .anyRequest().permitAll()
            )

            // ===== CẤU HÌNH LOGIN =====
            .formLogin(form -> form
                .loginPage("/admin/login")
                .loginProcessingUrl("/admin/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/admin", true) // true = luôn force về /admin sau khi login thành công
                .failureUrl("/admin/login?error=true")
                .permitAll()
            )

            // ===== CẤU HÌNH LOGOUT =====
            .logout(logout -> logout
                .logoutUrl("/admin/logout")
                .logoutSuccessUrl("/admin/login?logout=true")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll()
            );

        return http.build();
    }
}