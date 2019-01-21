package no.gnome.graphql;

public class User {

	private final String id;
	private final String name;
	private final int born;
	
	public User(String name, int born) {
		this(null, name, born);
	}

	public User(String id, String name, int born) {
		this.id = id;
		this.name = name;
		this.born = born;
	}

	public String getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public Integer getBorn() {
		return born;
	}
}
