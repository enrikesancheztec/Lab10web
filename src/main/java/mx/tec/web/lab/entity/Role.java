package mx.tec.web.lab.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

@Entity
public class Role {
	@Id
	@GeneratedValue
	private Long id;
	
	@NotNull	
	private String name;

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "roles")
	private Set<User> users;

	public Role() {}
	
	public Role(final Long id, final String name, final Set<User> users) {
		this.id = id;
		this.name = name;
		this.users = users;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + "]";
	}
}
