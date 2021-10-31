package mx.tec.web.lab.vo;

public enum RoleEnum {
	ADMIN(1, "Admin"),
	USER(2, "User");
	
	private final long id;
	private final String name;
	
	RoleEnum(final long id, final String name) {
		this.id = id;
		this.name = name;
	}
	
	public long getId() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}
}
