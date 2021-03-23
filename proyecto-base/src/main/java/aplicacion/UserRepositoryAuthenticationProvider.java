package aplicacion;

import java.util.ArrayList;
import java.util.List;

import javax.naming.AuthenticationException;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.mysql.cj.conf.PropertySet;
import com.mysql.cj.exceptions.ExceptionInterceptor;
import com.mysql.cj.protocol.AuthenticationProvider;
import com.mysql.cj.protocol.Protocol;
import com.mysql.cj.protocol.ServerSession;

@Component
public class UserRepositoryAuthenticationProvider implements AuthenticationProvider{

	@Autowired
	private RepositorioUsuarios repositorioUsuarios;
	
	@Override
	public Authentication authenticate(Authentication auth) throws AuthenticationException {
		Usuario user = repositorioUsuarios.findById(auth.getName());
		if (user == null) {
			throw new BadCredentialsException("User not found");
		}
		String password = (String) auth.getCredentials();
		if (!new BCryptPasswordEncoder().matches(password, user.getPasswordHash())) {
			throw new BadCredentialsException("Wrong password");
		}
		List<GrantedAuthority> roles = new ArrayList<>();
		for (String role : user.getRoles()) {
			roles.add(new SimpleGrantedAuthority(role));
		}
		
		return new UsernamePasswordAuthenticationToken(user.getName(), password, roles);
		
	}

	@Override
	public void init(Protocol prot, PropertySet propertySet, ExceptionInterceptor exceptionInterceptor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void connect(ServerSession serverSession, String userName, String password, String database) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void changeUser(ServerSession serverSession, String userName, String password, String database) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getEncodingForHandshake() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
