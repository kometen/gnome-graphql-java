package no.gnome.graphql;

import com.coxautodev.graphql.tools.GraphQLRootResolver;

public class Mutation implements GraphQLRootResolver {

	private final LinkRepository linkRepository;
	private final UserRepository userRepository;
	
	public Mutation(LinkRepository linkRepository, UserRepository userRepository) {
		this.linkRepository = linkRepository;
		this.userRepository = userRepository;
	}
	
	public Link createLink(String url, String description) {
		Link newLink = new Link(url, description);
		linkRepository.saveLink(newLink);
		return newLink;
	}

	public User createUser(String name, int born) {
		User newUser = new User(name, born);
		userRepository.saveUser(newUser);
		return newUser;
	}
}
