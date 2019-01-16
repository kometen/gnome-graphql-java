package no.gnome.graphql;

import java.util.List;

import com.coxautodev.graphql.tools.GraphQLRootResolver;

public class Query implements GraphQLRootResolver {

	private final LinkRepository linkRepository;
	private final UserRepository userRepository;
	
	public Query(LinkRepository linkRepository, UserRepository userRepository) {
		this.linkRepository = linkRepository;
		this.userRepository = userRepository;
	}
	
	public List<Link> allLinks() {
		return linkRepository.getAllLinks();
	}

	public List<User> allUsers() {
		return userRepository.getAllUsers();
	}
}
