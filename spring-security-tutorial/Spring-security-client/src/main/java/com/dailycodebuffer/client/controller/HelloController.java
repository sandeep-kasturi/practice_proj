package com.dailycodebuffer.client.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
// import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
// import org.springframework.web.reactive.function.client.WebClient;

import com.dailycodebuffer.client.model.JwtRequest;
import com.dailycodebuffer.client.model.JwtResponse;
import com.dailycodebuffer.client.service.UserDetailsServiceImpl;
import com.dailycodebuffer.client.utility.JWTUtility;


// import static org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction.oauth2AuthorizedClient;

@RestController
public class HelloController {

    ////Oauth2 related
    // @Autowired
    // private WebClient webClient;

    // @GetMapping("/api/hello")
    // public String hello(Principal principal) {
    //     return "Hello " +principal.getName()+", Welcome to Daily Code Buffer!!";
    // }

    // @GetMapping("/api/users")
    // public String[] users(
    //         @RegisteredOAuth2AuthorizedClient("api-client-authorization-code")
    //                 OAuth2AuthorizedClient client){
    //     return this.webClient
    //             .get()
    //             .uri("http://127.0.0.1:8090/api/users")
    //             .attributes(oauth2AuthorizedClient(client))
    //             .retrieve()
    //             .bodyToMono(String[].class)
    //             .block();
    // }

    @GetMapping("/hello")
    public String hello(){
        return "Welcome All";
    }

    @GetMapping("/User")
    public String hello1(){
        return "Welcome USER";
    }

    @GetMapping("/Admin")
    public String hello2(){
        return "Welcome ADMIN";
    }




    @Autowired
    private JWTUtility jwtUtility;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailServiceImpl;


    @GetMapping("/")
    public String home() {
        return "Welcome to Daily Code Buffer!!";
    }

    @PostMapping("/authenticate")
    public JwtResponse authenticate(@RequestBody JwtRequest jwtRequest) throws Exception{

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            jwtRequest.getUsername(),
                            jwtRequest.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }

        final UserDetails userDetails
                = userDetailServiceImpl.loadUserByUsername(jwtRequest.getUsername());

        final String token =
                jwtUtility.generateToken(userDetails);

        return  new JwtResponse(token);
    }
}