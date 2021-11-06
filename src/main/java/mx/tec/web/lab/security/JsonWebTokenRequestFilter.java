package mx.tec.web.lab.security;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import io.jsonwebtoken.ExpiredJwtException;
import mx.tec.web.lab.util.JsonWebTokenUtil;
import mx.tec.web.lab.manager.SecurityManager;

@Component
public class JsonWebTokenRequestFilter extends OncePerRequestFilter {
	@Autowired
	private SecurityManager securityManager;
	
	@Autowired
	private JsonWebTokenUtil tokenUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		final String requestTokenHeader = request.getHeader("Authorization");
		String username = null;
		String token = null;

		if (null != requestTokenHeader && requestTokenHeader.startsWith("Bearer ")) {
			token = requestTokenHeader.substring(7);

			try {
				username = tokenUtil.getUsernameFromToken(token);
			} catch (IllegalArgumentException iae) {
				logger.error("Unable to get JWT Token", iae);
			} catch (ExpiredJwtException eje) {
				logger.error("JWT Token has expired", eje);
			}
		} else {
			logger.warn("JWT Token does not begin with Bearer String");
		}

		if (null != username && null == SecurityContextHolder.getContext().getAuthentication()) {
			Optional<UsernamePasswordAuthenticationToken> authenticationToken = securityManager.authenticate(username, token);
			if (authenticationToken.isPresent()) {
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = authenticationToken.get();
				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
		}

		chain.doFilter(request, response);
	}
}
