package mx.tec.web.lab.controller;

import javax.annotation.Resource;
import javax.persistence.EntityExistsException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.tec.web.lab.manager.UserManager;
import mx.tec.web.lab.manager.SecurityManager;
import mx.tec.web.lab.vo.CredentialsVO;
import mx.tec.web.lab.vo.JsonWebTokenVO;
import mx.tec.web.lab.vo.UserVO;

@RestController
@CrossOrigin(origins = "${client.url}")
@RequestMapping("/ecom/api/v1")
public class UserController {
	private static final Logger log = LoggerFactory.getLogger(UserController.class);	
	
	/** A reference to the User Manager */
	@Resource
	private UserManager userManager;

	/** A reference to the User Manager */
	@Resource
	private SecurityManager securityManager;	
	
	@PostMapping("/user")
	public ResponseEntity<UserVO> addUser(@RequestBody UserVO user) {
		log.info("Adding user {}", user.getUsername());
		return new ResponseEntity<>(userManager.addUser(user), HttpStatus.CREATED);
	}	
	
	@PostMapping("/authenticate")
	public ResponseEntity<JsonWebTokenVO> createAuthenticationToken(@RequestBody CredentialsVO credentials) {
		log.info("Authenticating user {}", credentials.getUsername());		
		return ResponseEntity.ok(securityManager.authenticate(credentials));		
	}
	
    @ExceptionHandler(SecurityException.class)
    public ResponseEntity<String> onSecurityException(final SecurityException se) {
    	log.error("Invalid credentials", se);
        return new ResponseEntity<>(se.getMessage(), HttpStatus.UNAUTHORIZED);
    }
    
    @ExceptionHandler(EntityExistsException.class)
    public ResponseEntity<String> onEntityExistsException(final EntityExistsException eee) {
    	log.error("User already exists", eee);
        return new ResponseEntity<>(eee.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
