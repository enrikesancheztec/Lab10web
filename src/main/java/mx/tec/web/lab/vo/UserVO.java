package mx.tec.web.lab.vo;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserVO {
	private Long id;
	private String firstname;
	private String lastname;
	private String username;
	private String password;	
	private Set<RoleVO> roles;

	public UserVO() {}

	public UserVO(Long id, String firstname, String lastname, String username, String password, Set<RoleVO> roles) {
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
		this.password = password;
		this.roles = roles;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<RoleVO> getRoles() {
		return roles;
	}

	public void setRoles(Set<RoleVO> roles) {
		this.roles = roles;
	}
}