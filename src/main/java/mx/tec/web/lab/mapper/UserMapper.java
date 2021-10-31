/*
 * UserMapper
 * Version 1.0
 * October 30, 2021 
 * Copyright 2021 Tecnologico de Monterrey
 */
package mx.tec.web.lab.mapper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.annotation.Resource;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import mx.tec.web.lab.entity.Role;
import mx.tec.web.lab.entity.Sku;
import mx.tec.web.lab.entity.User;
import mx.tec.web.lab.vo.RoleEnum;
import mx.tec.web.lab.vo.RoleVO;
import mx.tec.web.lab.vo.UserVO;

/**
 * Mapper for User Objetcs 
 * @author Enrique Sanchez
 */
@Component
public class UserMapper {
	/**
	 * Reference to the Model Mapper
	 */
	@Resource
    private ModelMapper modelMapper;
	
	/**
	 * Convert from User Entity to User Value Object
	 * @param user User Entity to convert
	 * @return User Value Object conversion
	 */
	public UserVO convertToVO(final User user) {
		return modelMapper.map(user, UserVO.class);
	}	

	/**
	 * Convert from User Value Object to User Entity
	 * @param userVO User Value to convert
	 * @return User Entity conversion
	 */
	public User convertToEntity(final UserVO userVO) {
		return modelMapper.map(userVO, User.class);
	}	
	
	/**
	 * Convert from Optional User Entity to Optional User Value Object
	 * @param optional User Entity Optional to convert
	 * @return Optional User Value Object conversion
	 */
	public Optional<UserVO> convertToOptionalVO(final Optional<mx.tec.web.lab.entity.User> optional) {
		Optional<UserVO> userVO = Optional.empty();
		
		if (optional.isPresent()) {
			userVO = Optional.of(convertToVO(optional.get()));
		}

	    return userVO;
	}
	
	/**
	 * Convert from User Entity List to User Value Object List
	 * @param users User Entity List to convert
	 * @return User Value Object List conversion
	 */
	public List<UserVO> convertToVOList(final List<User> users) {
		final List<UserVO> userVOs = new ArrayList<>();
		
		for (final User user : users) {
			userVOs.add(convertToVO(user));
	    }

		return userVOs;
	}
}
