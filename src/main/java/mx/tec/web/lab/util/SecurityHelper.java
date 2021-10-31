package mx.tec.web.lab.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class SecurityHelper {
	public String hash(String clearText) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.encode(clearText);
	}
}
