package vote.backend.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import vote.backend.security.AuthTokenFilter;
import vote.backend.security.JWT.AuthEntryPointJwt;
import vote.backend.services.NemService.NemDetailsServiceImpl;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private NemDetailsServiceImpl nemDetailsServiceImpl;

  @Autowired
  private AuthEntryPointJwt authEntryPointJwt;

  @Bean
  public AuthTokenFilter authenticationJwtTokenFilter() {
    return new AuthTokenFilter();
  }

  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Override
  public void configure(
    AuthenticationManagerBuilder authenticationManagerBuilder
  )
    throws Exception {
    authenticationManagerBuilder
      .userDetailsService(nemDetailsServiceImpl)
      .passwordEncoder(passwordEncoder());
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
      .cors()
      .and()
      .csrf()
      .disable()
      .exceptionHandling()
      .authenticationEntryPoint(authEntryPointJwt)
      .and()
      .sessionManagement()
      .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
      .and()
      .authorizeRequests()
      .antMatchers(
        "/",
        "/swagger-ui/**",
        "/swagger-resources/**",
        "/v2/api-docs"
      )
      .permitAll()
      .antMatchers("/api/auth/**")
      .permitAll()
      .requestMatchers(
        req ->
          req.getParameter("type") != null &&
          req.getParameter("type").equals("extended")
      )
      .access("hasRole('ADMIN')")
      .antMatchers("/**")
      .permitAll() //disabling the spring authentication
      .anyRequest()
      .authenticated();

    http.addFilterBefore(
      authenticationJwtTokenFilter(),
      UsernamePasswordAuthenticationFilter.class
    );
  }
}

