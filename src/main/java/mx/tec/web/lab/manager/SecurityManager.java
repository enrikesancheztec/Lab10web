package mx.tec.web.lab.manager;

import java.util.Optional;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;

import mx.tec.web.lab.controller.ProductController;
import mx.tec.web.lab.util.JsonWebTokenUtil;
import mx.tec.web.lab.vo.CredentialsVO;
import mx.tec.web.lab.vo.JsonWebTokenVO;

@Service
public class SecurityManager {
	private static final Logger log = LoggerFactory.getLogger(SecurityManager.class);	
	
	@Resource
	UserDetailsService userDAO;
	
	@Resource
	AuthenticationManager authenticationManager;
	
	@Resource
	JsonWebTokenUtil jwtTokenUtil;
	
	public JsonWebTokenVO authenticate(CredentialsVO credentials) throws AuthenticationException {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(credentials.getUsername(), credentials.getPassword()));		
		final UserDetails userDetails = userDAO.loadUserByUsername(credentials.getUsername());
		return new JsonWebTokenVO(jwtTokenUtil.generateToken(userDetails));
	}
	
	
	public Optional<UsernamePasswordAuthenticationToken> authenticate(final String username, final String token) {
		Optional<UsernamePasswordAuthenticationToken> authenticationToken = Optional.empty();
		UserDetails userDetails = userDAO.loadUserByUsername(username);

		log.info("Authenticating user {} with token", username);
		
		if (jwtTokenUtil.validateToken(token, userDetails)) {
			UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
					userDetails, null, userDetails.getAuthorities());
			authenticationToken = Optional.of(usernamePasswordAuthenticationToken);
			log.info("Token for user {} is valid", username);
		}

		return authenticationToken;
	}	
}