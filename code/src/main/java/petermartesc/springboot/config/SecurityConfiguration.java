package petermartesc.springboot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import petermartesc.springboot.security.JwtFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
	
	@Autowired
	private JwtFilter jwtAuthFilter;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http
			.cors(cors->cors.disable())
			.csrf(csrf -> csrf.disable())
				.authorizeHttpRequests(auth -> auth
					.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()

					.requestMatchers(
					"/",  "/index.html", /*"/",*/
					"/swagger-ui/**", "/swagger-ui.html",
					"/v3/api-docs/**", "/swagger-resources/**",
					"/configuration/**", "/swagger*/**",

					"/v2/**", "/v3/**", "/webjars/**",
					"/websocket*/**", "/api/auth/**"
					).permitAll()
						//.requestMatchers("/api/v2/alumnos").hasAnyRole("USER", "ADMIN")
					//.requestMatchers("/api/v2/**").hasAnyRole("USER", "ADMIN")
					//.requestMatchers("/api/v3/**").hasRole("ADMIN")
					.requestMatchers("/api/v1/**").hasAnyRole("USER", "ADMIN")
					.anyRequest().authenticated()
				)
				.sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
		//System.out.println("PAPAAAAAAA");
		return http.getOrBuild();
	}
}