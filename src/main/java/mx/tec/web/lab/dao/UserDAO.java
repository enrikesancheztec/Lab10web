package mx.tec.web.lab.dao;

import java.util.Optional;

import mx.tec.web.lab.vo.UserVO;

public interface UserDAO {
	Optional<UserVO> findById(long id);
	Optional<UserVO> findByUsername(String username);
	public UserVO insert(final UserVO user);
}
