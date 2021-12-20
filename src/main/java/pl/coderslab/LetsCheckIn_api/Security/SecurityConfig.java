package pl.coderslab.LetsCheckIn_api.Security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public SpringDataUserDetailsService customUserDetailsService() {
        return new SpringDataUserDetailsService();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/user/app/**").hasRole("USER")
                .antMatchers("/user/account/**").hasRole("USER")
                .antMatchers("/reservation/**").hasRole("USER")
                .antMatchers("/apartment/add/**").hasRole("USER")
                .antMatchers("/apartment/edit/**").hasRole("USER")
                .antMatchers("/apartment/delete/**").hasRole("USER")
                .antMatchers("/room/add/**").hasRole("USER")
                .antMatchers("/room/edit/**").hasRole("USER")
                .antMatchers("/room/delete/**").hasRole("USER")
                .antMatchers("/bill/**").hasRole("USER")
                .and().formLogin().loginPage("/user/login").defaultSuccessUrl("/user/app")
                .and().logout().logoutSuccessUrl("/");
        http.cors().and().csrf().disable();
    }
}
