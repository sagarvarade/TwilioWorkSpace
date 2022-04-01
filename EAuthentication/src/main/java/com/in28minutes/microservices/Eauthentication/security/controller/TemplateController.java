package com.in28minutes.microservices.Eauthentication.security.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.in28minutes.microservices.Eauthentication.security.jwt.JwtConfig;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;

@RestController
public class TemplateController {

	private final JwtConfig jwtConfig;
    private final SecretKey secretKey;

    public TemplateController(JwtConfig jwtConfig,
                            SecretKey secretKey) {
        this.jwtConfig = jwtConfig;
        this.secretKey = secretKey;
    }
	
    @GetMapping("login")
    public String getLoginView() {
        return "login";
    }

    @GetMapping("courses")
    public String getCoursesView() {
        return "courses";
    }
    @PostMapping("tokenverify")
    public String getAccessdeniedView(@RequestBody VerfifyToken token1) {
    	System.out.println("token  "+token1);
    	boolean isTokenValid=false;
    	try {
            String token = token1.getToken().replace(jwtConfig.getTokenPrefix(), "");

            Jws<Claims> claimsJws = Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token);

            Claims body = claimsJws.getBody();
            String username = body.getSubject();

            var authorities = (List<Map<String, String>>) body.get("authorities");
            Set<SimpleGrantedAuthority> authoritiesSet = authorities.stream()
                    .map(m -> new SimpleGrantedAuthority(m.get("authority")))
                    .collect(Collectors.toSet());

            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    username,
                    null,
                    authoritiesSet);
            SecurityContextHolder.getContext().setAuthentication(authentication);
             isTokenValid=true;
        } catch (JwtException e) {
        	 isTokenValid=false;
           // throw new IllegalStateException("Token cannot be trusted.");
        }
    	finally {
    		System.out.println("Is token::: "+isTokenValid);
    	}
    	return String.valueOf(isTokenValid);
    }
    
    
    @PostMapping("issuetoken")
    public String getTokenForUser(@RequestBody TokenDetails token1) {
    	System.out.println("token  "+token1);
    	String token="";
    	try {
			RestTemplate restTemplate = new RestTemplate();
	        
	        final String baseUrl = "http://localhost:8200/login";
	        URI uri= new URI(baseUrl);
	        TokenDetails employee = new TokenDetails(token1.getUsername(),token1.getPassword());
	        
	        ResponseEntity<String> result = restTemplate.postForEntity(uri, employee, String.class);
	        
	    	//String resultString = result.getBody();
			org.springframework.http.HttpHeaders headers = result.getHeaders();
	        System.out.println("headers String "+headers);
	        System.out.println("headers String "+headers.get("Authorization"));
	        token=headers.get("Authorization").get(0);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
    	return token;
    }
}
