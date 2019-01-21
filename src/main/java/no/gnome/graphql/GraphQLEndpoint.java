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

	private static final LinkRepository linkRepository;
	private static final UserRepository userRepository;

	static {
        //Change to `new MongoClient("mongodb://<host>:<port>/hackernews")`
        //if you don't have Mongo running locally on port 27017
		MongoDatabase mongo = new MongoClient().getDatabase("graphql");
		linkRepository = new LinkRepository(mongo.getCollection("links"));

		DBConnection dbconn = new DBConnection();
		userRepository = new UserRepository(dbconn.getDBConnection());
	}
	
	public GraphQLEndpoint() {
		super(buildSchema());
	}
	
	private static GraphQLSchema buildSchema() {
		return SchemaParser.newParser()
				.file("schema.graphqls")
				.resolvers(new Query(linkRepository, userRepository), new Mutation(linkRepository, userRepository))
				.build()
				.makeExecutableSchema();
	}
}
