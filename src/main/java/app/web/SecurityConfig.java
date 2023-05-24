package app.web;

import app.model.UserRole;
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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

/**
 * Класс реализует настройки доступа по таким-то энпонтам и т. д.(дописать)
 */

@CrossOrigin
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    public SecurityConfig(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    public SecurityConfig(boolean disableDefaults, UserDetailsServiceImpl userDetailsService) {
        super(disableDefaults);
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService); //говорим спрингу использовать наш userDetailsService

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        //это если мы не используем шифрование паролей
        //       return (PasswordEncoder) NoOpPasswordEncoder.getInstance();
        //а это, если используем
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .httpBasic() //включает возможность аутентифицироваться через постман
                .and()
                .csrf().disable()
                .cors().disable()
                .authorizeRequests()
                .antMatchers("/", "/registerClient.html", "/registerRecruiter.html", "/registerCandidate.html",
                        "/auth.html", "/order.html", "/order_candidate.html", "/candidate.html","/test_index.html")
                .permitAll()
                .antMatchers(HttpMethod.POST, "/users/register/*")
                .permitAll()
                .antMatchers(HttpMethod.POST, "/orders/save", "/orders/save/*")
                .permitAll()
                //все остальные эндпониты, начинающиеся на /orders будут требовать роль HR или ADMIN
                .antMatchers("/orders/**")
                .hasAnyRole(UserRole.HR.name(), UserRole.ADMIN.name())
                .anyRequest()
                .authenticated();

        //настройки для формы авторизации (входа в аккаунт)
        http.formLogin()
                .loginProcessingUrl("/auth")//авторизоваться
//                .defaultSuccessUrl("/")
                .usernameParameter("username")
                .passwordParameter("password")
                .permitAll();

        //настройки для выхода из аккаунта
        http.logout()
                .logoutUrl("/exit")
                .logoutSuccessUrl("/logout")
                .invalidateHttpSession(true);

    }


}
