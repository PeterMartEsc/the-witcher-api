package petermartesc.springboot.security;

import com.auth0.jwt.exceptions.JWTVerificationException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import petermartesc.springboot.repository.UserRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;


@Component
public class JwtFilter extends OncePerRequestFilter {
	
	public static final String authHeader="Authorization";
	public static final String authHeaderTokenPrefix="Bearer "; //ESPACIO NECESARIO!!

    @Autowired
    private JwtService jwtTokenManager;

    @Autowired
    private UserRepository usuarioRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

		String path = request.getRequestURI();
        
    	
    	//rutas permitidas sin estar autenticado
		/*String rutasPermitidas[]= { *//*"/",*//*  "/witcher/index.html",
									"/witcher/swagger-ui/", "/witcher/swagger-ui.html",
									"/witcher/v3/api-docs/", "/witcher/swagger-resources/",
									"/configuration/", "/witcher/swagger/",

									"/witcher/v2/", "/witcher/v3/", "/webjars/",
									"/websocket/", "/witcher/api/v1/"};*/
		String rutasPermitidas[] = {
				/*"/witcher/",*/ "/index.html",
				"/swagger-ui", "/swagger-ui/",
				"/swagger-ui/index.html", "/swagger-ui.html",
				"/v3/api-docs", "/v3/api-docs/",
				"/swagger-resources", "/swagger-resources/",
				"/configuration/", "/webjars/",
				"/api/auth/", "/websocket/"
		};


    	for (String ruta : rutasPermitidas) {
            if (path.startsWith(ruta)) {
                // Permitir la solicitud sin autenticación
				filterChain.doFilter(request, response);
                return;
            }
        }
    	//el token viene en un header Authorization
        String header = request.getHeader(authHeader);
        
        //típicamente se precede el token con bearer:  Bearer token
        if (header != null && header.startsWith(authHeaderTokenPrefix)) {
        	
            String token = header.substring(authHeaderTokenPrefix.length());
            try {
            	Map<String, String> mapInfoToken = jwtTokenManager.validateAndGetClaims(token);
            	
            	//System.out.println(mapInfoToken);
            	final String nombreusuario=mapInfoToken.get("username");
            
            	final String rol = mapInfoToken.get("role");

            	//UserDetails en Spring Security es un interfaz basado en Principal de java
            	//y es la forma que tiene Spring de mantener la información de usuario "autenticado"
            	//en el contexto de seguridad. Nos permite guardar la información de username
            	//y authorities ( los roles si se admiten múltiples roles ) Creamos un objeto de clase anónima UserDetails:
            	UserDetails userDetails = new UserDetails() {
            		
            		String username=nombreusuario;

					@Override
					public Collection<? extends GrantedAuthority> getAuthorities() {
					    List<GrantedAuthority> authorities = new ArrayList<>();
					
					    authorities.add(new SimpleGrantedAuthority(/*"ROLE_" + */rol));
					    return authorities;
					}

					@Override
					public String getPassword() { return null; 	}

					@Override
					public String getUsername() {
						return username;
					}

					//Obligaba a meterlos
					@Override
					public boolean isAccountNonExpired() {
						return false;
					}

					@Override
					public boolean isAccountNonLocked() {
						return false;
					}

					@Override
					public boolean isCredentialsNonExpired() {
						return false;
					}

					@Override
					public boolean isEnabled() {
						return false;
					}

				};


            	
        		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
        				userDetails, 
        				null,
        				userDetails.getAuthorities()
        		);
        		
        		authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        		SecurityContextHolder.getContext().setAuthentication(authToken);
        		


        		filterChain.doFilter(request, response);

            } catch (JWTVerificationException e) {
				//System.out.println("respuesta no es valida");
//				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//				response.getWriter().write("Token inválido o expirado");
//				response.getWriter().flush();
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
			
        }
    }
}
