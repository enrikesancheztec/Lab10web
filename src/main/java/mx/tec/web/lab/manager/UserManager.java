package mx.tec.web.lab.manager;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.annotation.Resource;
import javax.persistence.EntityExistsException;

import org.springframework.stereotype.Service;

import mx.tec.web.lab.util.SecurityHelper;
import mx.tec.web.lab.dao.UserDAO;
import mx.tec.web.lab.vo.RoleEnum;
import mx.tec.web.lab.vo.RoleVO;
import mx.tec.web.lab.vo.UserVO;

@Service
public class UserManager {
	@Resource
	public UserDAO userDAO;
	
	@Resource
	private SecurityHelper securityHelper;
	
	public Optional<UserVO> getUser(final long id) {
		return userDAO.findById(id);
	}

	public UserVO addUser(final UserVO user) {
		Optional<UserVO> foundUser = userDAO.findByUsername(user.getUsername());
		
		if (foundUser.isPresent()) {
			throw new EntityExistsException("User already exists " + user.getUsername());
		} else {
			user.setPassword(securityHelper.hash(user.getPassword()));
			Set<RoleVO> roles = new HashSet<>();
			RoleVO userRole = new RoleVO(RoleEnum.USER.getId(), RoleEnum.USER.getName());
			
			roles.add(userRole);			

			user.setRoles(roles);
			return userDAO.insert(user);
		}
	}

}
