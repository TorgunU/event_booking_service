package ru.booking.event_booking_service.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ru.booking.event_booking_service.handler.UserAccessDeniedHandler;
import ru.booking.event_booking_service.handler.UserAuthenticationEntryPoint;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
public class SecurityConfiguration {
    private final JwtRequestFilter jwtRequestFilter;
    private final UserAuthenticationEntryPoint authenticationEntryPoint;
    private final UserAccessDeniedHandler accessDeniedHandler;

    @Autowired
    public SecurityConfiguration(JwtRequestFilter jwtRequestFilter,
                                 UserAuthenticationEntryPoint authenticationEntryPoint,
                                 UserAccessDeniedHandler accessDeniedHandler) {
        this.jwtRequestFilter = jwtRequestFilter;
        this.authenticationEntryPoint = authenticationEntryPoint;
        this.accessDeniedHandler = accessDeniedHandler;
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.debug(true).ignoring()
                .requestMatchers("/css/**",
                        "/js/**",
                        "/img/**",
                        "/lib/**",
                        "/favicon.ico",
                        "/swagger-ui/**",
                        "/v3/api-docs/**",
                        "/v3/api-docs.yaml",
                        "/openapi.yaml/**",
                        "/swagger-ui/openapi.yaml/**", // не работает
                        "/configuration/ui",
                        "/v3/api-docs/swagger-config/**",
                        "/swagger-resources/**",
                        "/configuration/security",
                        "/swagger-ui.html",
                        "/webjars/**",
                        "http://localhost:8080/swagger-ui/index.html");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(sessionManagement -> sessionManagement
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling(exceptionHandling -> exceptionHandling
                        .authenticationEntryPoint(authenticationEntryPoint)
                        .accessDeniedHandler(accessDeniedHandler))
                .authorizeHttpRequests(authorizeHttpRequest ->
                        authorizeHttpRequest
                                .requestMatchers(HttpMethod.POST, "users/auth").permitAll()
                                .requestMatchers(HttpMethod.POST, "users").permitAll()
                                .requestMatchers(HttpMethod.GET, "/users/{userId}").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.POST, "/locations").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/locations/{locationID}").hasAnyRole("ADMIN", "USER")
                                .requestMatchers(HttpMethod.PUT, "/locations/{locationID}").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/locations/**").hasAnyRole("ADMIN", "USER")
                                .anyRequest().authenticated())
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}