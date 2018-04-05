package no.gnome.graphql;

import com.coxautodev.graphql.tools.SchemaParser;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

import javax.servlet.annotation.WebServlet;

import graphql.schema.GraphQLSchema;
import graphql.servlet.SimpleGraphQLServlet;

@WebServlet(urlPatterns = "/graphql")
public class GraphQLEndpoint extends SimpleGraphQLServlet {

	private static final long serialVersionUID = 1L;
	private static final LinkRepository linkRepository;

	static {
		MongoDatabase mongo = new MongoClient().getDatabase("graphql");
		linkRepository = new LinkRepository(mongo.getCollection("links"));
	}
	
	public GraphQLEndpoint() {
		super(buildSchema());
	}
	
	private static GraphQLSchema buildSchema() {
		return SchemaParser.newParser()
				.file("schema.graphqls")
				.resolvers(new Query(linkRepository), new Mutation(linkRepository))
				.build()
				.makeExecutableSchema();
	}
}
