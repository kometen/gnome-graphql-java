package no.gnome.graphql;

public class Person {

	private final String id;
	private final String name;
	private final Integer born;
	
	public Person(String name, Integer born) {
		this(null, name, born);
	}

	public Person(String id, String name, Integer born) {
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
