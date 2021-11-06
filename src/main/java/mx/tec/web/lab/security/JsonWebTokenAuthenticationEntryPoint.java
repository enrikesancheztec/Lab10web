package mx.tec.web.lab.security;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class JsonWebTokenAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {
	private static final long serialVersionUID = 424988175885801316L;

	@Override
	public void commence(final HttpServletRequest request, final HttpServletResponse response, final AuthenticationException authException)
			throws IOException, ServletException {
		
        if (HttpMethod.OPTIONS.matches(request.getMethod())) {
            response.setStatus( HttpServletResponse.SC_OK );
            response.setHeader( HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, request.getHeader( HttpHeaders.ORIGIN ) );
            response.setHeader( HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, request.getHeader( HttpHeaders.ACCESS_CONTROL_REQUEST_HEADERS ) );
            response.setHeader( HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, request.getHeader( HttpHeaders.ACCESS_CONTROL_REQUEST_METHOD ) );
            response.setHeader( HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "*" );
            response.setHeader( HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS, "true" );
        } else {
            response.sendError( HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized" );
        }		
	}
}
