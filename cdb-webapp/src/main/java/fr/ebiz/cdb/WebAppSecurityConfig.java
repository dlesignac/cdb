package fr.ebiz.cdb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
public class WebAppSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService authenticationService;

    /**
     * @param auth AuthenticationManagerBuilder
     * @throws Exception Exception
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        ShaPasswordEncoder encoder = new ShaPasswordEncoder(256);
        auth.userDetailsService(authenticationService).passwordEncoder(encoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .antMatcher("/**")
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/computer/**").authenticated()
                .antMatchers("/", "/static/**", "/dashboard").permitAll()
                .antMatchers("/rest/**").permitAll()
                .and()
                .formLogin()
                .permitAll()
                .loginPage("/login")
                .defaultSuccessUrl("/dashboard?loginSuccessful=true")
                .failureUrl("/login?error=true")
                .usernameParameter("username").passwordParameter("password")
                .and()
                .logout()
                .invalidateHttpSession(true)
                .logoutSuccessUrl("/dashboard?logoutSuccessful=true");
    }
}
