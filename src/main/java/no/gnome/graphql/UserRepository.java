package no.gnome.graphql;

import java.util.ArrayList;
import java.util.List;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.jsoniter.JsonIterator;
import com.jsoniter.any.Any;
import com.jsoniter.output.EncodingMode;
import com.jsoniter.output.JsonStream;
import com.jsoniter.spi.DecodingMode;

public class UserRepository {

	private Connection conn;

	private String query = "";

	public UserRepository() {
	}

	public UserRepository(Connection conn) {
		this.conn = conn;
	}

	public List<User> getAllUsers() {
		List<User> allUsers = new ArrayList<>();
		query = "select * from users";
		try {
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(query);
			while (rs.next()) {
				Any jsonObject = JsonIterator.deserialize(rs.getString("json_data"));
				System.out.println(rs.getString(1));
				System.out.println(jsonObject.get("name").toString());
				allUsers.add(new User(rs.getString(1), jsonObject.get("name").toString(), jsonObject.get("born").toInt()));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return allUsers;
	}

	public void saveUser(User user) {
		String json = JsonStream.serialize(new User(user.getName(), user.getBorn()));
		System.out.println(json);
		query = "insert into users (json_data) values (?::jsonb)";
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1, json);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

/*	private final MongoCollection<Document> links;
	
	public PersonRepository(MongoCollection<Document> links) {
		this.links = links;
	}
	
	public Link findById(String id) {
		Document doc = links.find(eq("_id", new ObjectId(id))).first();
		return link(doc);
	}
	
	private Link link(Document doc) {
		return new Link(
				doc.get("_id").toString(),
				doc.getString("url"),
				doc.getString("description"));
	}*/
}
