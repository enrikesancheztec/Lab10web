package mx.tec.web.lab.dao;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import mx.tec.web.lab.entity.Role;
import mx.tec.web.lab.entity.User;
import mx.tec.web.lab.mapper.UserMapper;
import mx.tec.web.lab.repository.UserRepository;
import mx.tec.web.lab.vo.UserVO;

@Component
public class UserDAOImpl implements UserDAO, UserDetailsService {
	/** A reference to the User Repository */
	@Resource
	private UserRepository userRepository;
	
	/** A reference to the User Mapper */
	@Resource
    private UserMapper userMapper;
	
	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(final String username) {
		Optional<User> user = userRepository.findByUsername(username);
		if (!user.isPresent()) {
			throw new UsernameNotFoundException(username);
		}

		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

		User foundUser = user.get();
		
		for (Role role : foundUser.getRoles()) {
			grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
		}

		return new org.springframework.security.core.userdetails.User(
				foundUser.getUsername(), 
				foundUser.getPassword(),
				grantedAuthorities);
	}

	@Override
	public Optional<UserVO> findById(final long id) {
		return userMapper.convertToOptionalVO(userRepository.findById(id));
	}

	@Override
	public UserVO insert(final UserVO user) {
		return userMapper.convertToVO(userRepository.save(userMapper.convertToEntity(user)));
	}

	@Override
	public Optional<UserVO> findByUsername(String username) {
		return userMapper.convertToOptionalVO(userRepository.findByUsername(username));
	}
}
