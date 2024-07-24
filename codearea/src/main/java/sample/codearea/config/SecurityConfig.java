package sample.codearea.config;

import java.util.Arrays;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@EnableWebSecurity
@EnableMethodSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    public static BCryptPasswordEncoder bCryptPasswordEncoder() {   // for hash encrypt
        return new BCryptPasswordEncoder();
    }
    //
    // @Bean
    // public WebSecurityCustomizer webSecurityCustomizer() {  // for ignore the confirmation about static resources
    //     return (web) -> web.ignoring()
    //             .requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    // }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors().and()
                .csrf(AbstractHttpConfigurer::disable)
                .headers(headers -> headers.frameOptions().disable())
                .authorizeHttpRequests(requests ->requests
                        .requestMatchers("/**", "/user/**", "/questions/**", "/h2-console/**").permitAll()  // all paths can be accessed
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/user/login")  // user defined login page path
                        .loginProcessingUrl("/perform_login")
                        .defaultSuccessUrl("/", true)
                        .permitAll()
                )
                .logout(LogoutConfigurer::permitAll);
        return http.build();
    }

}
