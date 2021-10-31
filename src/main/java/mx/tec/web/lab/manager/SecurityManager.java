package mx.tec.web.lab.manager;

import javax.annotation.Resource;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import mx.tec.web.lab.util.JsonWebTokenUtil;
import mx.tec.web.lab.vo.CredentialsVO;
import mx.tec.web.lab.vo.JsonWebTokenVO;

@Service
public class SecurityManager {
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
}