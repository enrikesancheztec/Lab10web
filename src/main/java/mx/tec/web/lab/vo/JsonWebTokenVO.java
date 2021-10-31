package mx.tec.web.lab.vo;

import java.io.Serializable;

public class JsonWebTokenVO implements Serializable {
	private static final long serialVersionUID = 3383380352339583679L;

	private final String token;

	public JsonWebTokenVO(String token) {
		this.token = token;
	}

	public String getToken() {
		return this.token;
	}
}
