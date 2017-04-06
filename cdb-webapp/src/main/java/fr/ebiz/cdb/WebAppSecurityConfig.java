package fr.ebiz.cdb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.authentication.www.DigestAuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.DigestAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebAppSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService authenticationService;

    /**
     * @return DigestAuthenticationEntryPoint
     */
    @Bean
    public DigestAuthenticationEntryPoint digestEntryPoint() {
        DigestAuthenticationEntryPoint authenticationEntryPoint = new DigestAuthenticationEntryPoint();
        authenticationEntryPoint.setRealmName("Digest Realm");
        authenticationEntryPoint.setKey("aTdJldKH8VDsa");
        authenticationEntryPoint.setNonceValiditySeconds(10);
        return authenticationEntryPoint;
    }

    /**
     * @param digestEntryPoint digestEntryPoint
     * @return DigestAuthenticationFilter
     */
    @Bean
    public DigestAuthenticationFilter digestFilter(
            DigestAuthenticationEntryPoint digestEntryPoint) {
        DigestAuthenticationFilter authenticationFilter = new DigestAuthenticationFilter();
        authenticationFilter.setUserDetailsService(authenticationService);
        authenticationFilter.setAuthenticationEntryPoint(digestEntryPoint);
        return authenticationFilter;
    }

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
                .exceptionHandling()
                .authenticationEntryPoint(digestEntryPoint())
                .and()
                .addFilterAfter(digestFilter(digestEntryPoint()), BasicAuthenticationFilter.class)
                .antMatcher("/**")
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/computer/**").authenticated()
                .antMatchers("/", "/static/**", "/dashboard").permitAll()
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
