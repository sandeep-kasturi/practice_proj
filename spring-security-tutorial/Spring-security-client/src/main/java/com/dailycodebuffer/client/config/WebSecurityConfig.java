package com.dailycodebuffer.client.config;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.dailycodebuffer.client.filter.JwtFilter;
import com.dailycodebuffer.client.service.UserDetailsServiceImpl;
import com.dailycodebuffer.client.service.UserService;
import com.dailycodebuffer.client.service.UserServiceImpl;




@Configuration
@EnableWebSecurity
public class WebSecurityConfig{

    private static final String[] WHITE_LIST_URLS = { "/hello*", "/register*", "/verifyRegistration*", "/resendVerifyToken*", "/resetPassword*", "/savePassword*", "/changePassword*"};

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(11);
    } 

    // @Bean
    // public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
       
    //     //Bypassing default security login
    //     http.cors((cors) -> cors.disable()); // Disabling CORS protection
    //     http.csrf((csrf) -> csrf.disable()); // Disabling CSRF protection
    //     http.authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests.requestMatchers(WHITE_LIST_URLS).permitAll());

    //     //for oauth2 not working
    //     // http.authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests.requestMatchers("/api/**").authenticated());
    //     // http.oauth2Login(oauth2login -> oauth2login.loginPage("/oauth2/authorization/api-client-oidc"))
    //     //     .oauth2Client(Customizer.withDefaults());
       
    //     return http.build();
    // }




    // //form based login with JDBC authentication and MySQL DB
    // @Autowired
    // private DataSource dataSource;
     
    // @Autowired
    // public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
    //     auth.jdbcAuthentication().passwordEncoder(new BCryptPasswordEncoder())
    //         .dataSource(dataSource)
    //         .usersByUsernameQuery("select first_name, password, enabled from User where first_name=?")
    //         .authoritiesByUsernameQuery("select first_name, role from User where first_name=?")
    //     ;
    // }
 
    // @Bean
    // public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    //     http.authorizeHttpRequests((authorizeHttpRequests)->authorizeHttpRequests.anyRequest().authenticated());
    //     http.formLogin((formLogin)->formLogin.permitAll());
    //     http.logout((logout)->logout.permitAll());
    //     return http.build();     
    // }
    



    // //form based login with JPA authentication and MySQL DB (applies only for scenarios like each user has only one role)
    
    // // @Override
    // // protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    // //     auth.userDetailsService(userDetailsService);
    // // }
        
    // @Bean
    // public UserDetailsService userDetailsService() {
    //     return new UserDetailsServiceImpl();
    // }

    // @Bean
    // public DaoAuthenticationProvider authenticationProvider() {
    //   DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
       
    //   authProvider.setUserDetailsService(userDetailsService());
    //   authProvider.setPasswordEncoder(passwordEncoder());
   
    //   return authProvider;
    // }
    
    // @Bean
    // public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    //     http.authenticationProvider(authenticationProvider());

    //     // http.authorizeHttpRequests((authorizeHttpRequests)->authorizeHttpRequests.anyRequest().authenticated());
    //     http.authorizeHttpRequests((AuthorizeHttpRequests)->AuthorizeHttpRequests
    //     .requestMatchers("/Admin/**").hasRole("ADMIN")
    //     .requestMatchers("/User/**").hasAnyRole("ADMIN", "USER")
    //     .requestMatchers("/hello").permitAll());
    //     http.formLogin((formLogin)->formLogin.permitAll());
    //     http.logout((logout)->logout.permitAll());
    //     return http.build();     
    // }




    // //form based login with JPA authentication and MySQL DB (applies for scenarios like each user can have multiple roles)
    // // @Autowired
    // // private UserDetailsService userDetailsService;
    

    // // @Override
    // // protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    // //     auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(passwordEncoder());
    // // }

    // @Bean
    // public UserDetailsService userDetailsService() {
    //     return new UserDetailsServiceImpl();
    // }

    // @Bean
    // public DaoAuthenticationProvider authenticationProvider() {
    //   DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
       
    //   authProvider.setUserDetailsService(userDetailsService());
    //   authProvider.setPasswordEncoder(passwordEncoder());
   
    //   return authProvider;
    // }

    // @Bean
    // public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    //     // http.authorizeHttpRequests((authorizeHttpRequests)->authorizeHttpRequests.anyRequest().authenticated());
    //     http.authorizeHttpRequests((AuthorizeHttpRequests)->AuthorizeHttpRequests
    //     .requestMatchers("/Admin/**").hasRole("ADMIN")
    //     .requestMatchers("/User/**").hasAnyRole("ADMIN", "USER")
    //     .requestMatchers("/hello").permitAll());
    //     http.formLogin((formLogin)->formLogin.permitAll());
    //     http.logout((logout)->logout.permitAll());
    //     return http.build();     
    // }



    //JWT Authentication
    @Autowired
    private UserDetailsServiceImpl userDetailServiceImpl;

    @Autowired
    private JwtFilter jwtFilter;

    @Autowired
    private AuthenticationConfiguration authConfiguration;

    @Autowired
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(userDetailServiceImpl);
    }

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf((csrf)->csrf.disable());
        http.authorizeHttpRequests((authorizeHttpRequests)->authorizeHttpRequests.requestMatchers("/authenticate").permitAll()
                                                                                 .requestMatchers(WHITE_LIST_URLS).permitAll() 
                                                                                 .anyRequest().authenticated());
        http.sessionManagement((sessionManagement)->sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();

    }
    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return authConfiguration.getAuthenticationManager();
    }



    //SMTP Email send function
    @Bean
    public JavaMailSender javaMailSender() { 
          return new JavaMailSenderImpl();
    }


    // //Oauth2 config
    // @Bean
    // SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    //     http
    //             .cors((cors)->cors.disable())
    //             .csrf((csrf)->csrf.disable())
    //             .authorizeHttpRequests((authorizeHttpRequests)->authorizeHttpRequests.requestMatchers(WHITE_LIST_URLS).permitAll().requestMatchers("/api/**").authenticated())
    //             .oauth2Login(oauth2login ->
    //                     oauth2login.loginPage("/oauth2/authorization/api-client-oidc"))
    //             .oauth2Client(Customizer.withDefaults());

    //     return http.build();
    // }

}