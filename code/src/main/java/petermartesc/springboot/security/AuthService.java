package petermartesc.springboot.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import petermartesc.springboot.model.Role;
import petermartesc.springboot.model.User;
import petermartesc.springboot.repository.RoleRepository;
import petermartesc.springboot.repository.UserRepository;

import java.util.Date;
import java.util.UUID;


@Service
public class AuthService {
	
	
    @Autowired
    private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;
	
    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

	/*@Autowired
	private MailService mailService;*/

    
	public String register(String username, String password/*, String email*/) {
		User user = new User();
		user.setName(username);
		user.setPassword(passwordEncoder.encode(password));
		Role roleUser = roleRepository.findById(2).orElse(null);
		if(roleUser == null){
			return null;
		}

		user.setRole(roleUser);
		User saved = userRepository.save(user);
		
		if( saved != null) {
			String generatedToken = jwtService.generateToken(user.getName(), user.getRole().getRoleName());
			return generatedToken;
		}else {
			return null;
		}
	}    
    


	public String authenticate(String username, String password)  {
		String generatedToken = null;
		User user = userRepository.findByNombre(username).orElse(null);
		//System.out.println(user.getNombre());
		if (user != null) {
			//System.out.println("no es nulo");
			if (passwordEncoder.matches(password, user.getPassword())) {
				//System.out.println("tiene contraseña y coincide con la de la bbdd");
				generatedToken = jwtService.generateToken(user.getName(), user.getRole().getRoleName());
			}
		}

		return generatedToken;
	}
}

