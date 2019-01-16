package no.gnome.graphql;

import com.coxautodev.graphql.tools.SchemaParser;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

import javax.servlet.annotation.WebServlet;

import graphql.schema.GraphQLSchema;
import graphql.servlet.SimpleGraphQLServlet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(urlPatterns = "/graphql")
public class GraphQLEndpoint extends SimpleGraphQLServlet {

	public static final String postgres_url = "jdbc:postgresql://localhost:5432/graphql";
	public static final String postgres_user = "claus";
	public static final String postgres_pwd = "";
	private static final long serialVersionUID = 1L;
	private static final LinkRepository linkRepository;

	static {
        //Change to `new MongoClient("mongodb://<host>:<port>/hackernews")`
        //if you don't have Mongo running locally on port 27017
		MongoDatabase mongo = new MongoClient().getDatabase("graphql");
		linkRepository = new LinkRepository(mongo.getCollection("links"));

	    try {
	    	Connection con = DriverManager.getConnection(postgres_url, postgres_user, postgres_pwd);
	        Statement st = con.createStatement();
	        ResultSet rs = st.executeQuery("SELECT VERSION()");

	        if (rs.next()) {
	            System.out.println(rs.getString(1));
	        }
	    } catch (SQLException ex) {
	    }

	}
	
	public GraphQLEndpoint() {
		super(buildSchema());
	}
	
	private static GraphQLSchema buildSchema() {
		UserRepository userRepository = new UserRepository();
		return SchemaParser.newParser()
				.file("schema.graphqls")
				.resolvers(new Query(linkRepository, userRepository), new Mutation(linkRepository))
				.build()
				.makeExecutableSchema();
	}
}
