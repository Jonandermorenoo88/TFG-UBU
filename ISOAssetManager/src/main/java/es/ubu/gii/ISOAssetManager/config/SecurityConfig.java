package es.ubu.gii.ISOAssetManager.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private CustomLoginSuccessHandler loginSuccessHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.authorizeHttpRequests(auth -> auth
        // Público
        .requestMatchers("/", "/inicio", "/login", "/registro", "/esperarol",
                        "/css/**", "/js/**", "/images/**", "/*.css").permitAll()

        // Paneles
        .requestMatchers("/panel-admin").hasRole("ADMIN")
        .requestMatchers("/panel-auditor").hasRole("AUDITOR")

        // Administración
        .requestMatchers("/usuarios/**", "/admin/**").hasRole("ADMIN")

        // Auditor
        .requestMatchers("/auditor/**").hasAnyRole("AUDITOR","ADMIN")

        // ==== CUESTIONARIOS / BLOQUES
        .requestMatchers(HttpMethod.GET,  "/empresas/*/cuestionario/grupo/organizacionales/**")
        .hasAnyRole("ADMIN","AUDITOR","DIRECCION")
        .requestMatchers(HttpMethod.GET,  "/empresas/*/cuestionario/grupo/personas/**")
        .hasAnyRole("ADMIN","AUDITOR","RRHH")
        .requestMatchers(HttpMethod.POST, "/empresas/*/cuestionario/grupo/personas/**")
        .hasAnyRole("ADMIN","AUDITOR","RRHH")
        .requestMatchers(HttpMethod.GET,  "/empresas/*/cuestionario/grupo/fisicos/**")
        .hasAnyRole("ADMIN","AUDITOR","FACILITIES")
        .requestMatchers(HttpMethod.GET,  "/empresas/*/cuestionario/grupo/tecnologicos/**")
        .hasAnyRole("ADMIN","AUDITOR","IT/TECNICO")

        .requestMatchers("/anexos/5/**").hasAnyRole("ADMIN","AUDITOR","DIRECCION")
        .requestMatchers("/anexos/6/**").hasAnyRole("ADMIN","AUDITOR","RRHH")
        .requestMatchers("/anexos/7/**").hasAnyRole("ADMIN","AUDITOR","FACILITIES")
        .requestMatchers("/anexos/8/**").hasAnyRole("ADMIN","AUDITOR","IT/TECNICO")

        .requestMatchers(HttpMethod.GET, "/empresas", "/empresas/", "/empresas/*/bloques")
        .hasAnyRole("ADMIN","AUDITOR","RRHH","DIRECCION","FACILITIES","IT/TECNICO")


        .requestMatchers(HttpMethod.GET, "/activos/**")
        .hasAnyRole("ADMIN","AUDITOR","RRHH","DIRECCION","FACILITIES","IT/TECNICO")
        .requestMatchers(HttpMethod.POST, "/activos/**").hasAnyRole("ADMIN","AUDITOR")
        .requestMatchers(HttpMethod.PUT,  "/activos/**").hasAnyRole("ADMIN","AUDITOR")
        .requestMatchers(HttpMethod.DELETE,"/activos/**").hasAnyRole("ADMIN","AUDITOR")

        .anyRequest().authenticated()
    )
    .formLogin(f -> f.loginPage("/login").successHandler(loginSuccessHandler).permitAll())
    .logout(l -> l.logoutUrl("/logout").logoutSuccessUrl("/login?logout").permitAll())
    .exceptionHandling(e -> e.accessDeniedPage("/error/403"));

    return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
