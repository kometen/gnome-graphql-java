package no.gnome.graphql;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.client.MongoCollection;

import org.bson.Document;
import org.bson.types.ObjectId;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.regex;

public class UserRepository {

	private final List<User> users;

	public UserRepository() {
		users = new ArrayList<>();
		users.add(new User("Claus Guttesen", 1967));
		users.add(new User("Eline Kleppenes", 2000));
	}

	public List<User> getAllUsers() {
		return users;
	}

	public void saveUser(User user) {
		users.add(user);
	}

/*	private final MongoCollection<Document> links;
	
	public PersonRepository(MongoCollection<Document> links) {
		this.links = links;
	}
	
	public Link findById(String id) {
		Document doc = links.find(eq("_id", new ObjectId(id))).first();
		return link(doc);
	}
	
	public List<Link> getAllLinks() {
		List<Link> allLinks = new ArrayList<>();
		for (Document doc : links.find()) {
			allLinks.add(link(doc));
		}
		return allLinks;
	}
	
	public void saveLink(Link link) {
		Document doc = new Document();
		doc.append("url", link.getUrl());
		doc.append("description", link.getDescription());
		links.insertOne(doc);
	}
	
	private Link link(Document doc) {
		return new Link(
				doc.get("_id").toString(),
				doc.getString("url"),
				doc.getString("description"));
	}*/
}
