package mx.tec.web.lab.vo;

import java.io.Serializable;

public class CredentialsVO implements Serializable {
	private static final long serialVersionUID = -3050268746076095589L;

	private String username;
	private String password;

	public CredentialsVO() {}

	public CredentialsVO(final String username, final String password) {
		this.setUsername(username);
		this.setPassword(password);
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
