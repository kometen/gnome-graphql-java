package no.gnome.graphql;

import java.util.List;

import com.coxautodev.graphql.tools.GraphQLRootResolver;

public class Query implements GraphQLRootResolver {

	private final LinkRepository linkRepository;
	private final PersonRepository personRepository;
	
	public Query(LinkRepository linkRepository, PersonRepository personRepository) {
		this.linkRepository = linkRepository;
		this.personRepository = personRepository;
	}
	
	public List<Link> allLinks() {
		return linkRepository.getAllLinks();
	}

	public List<Person> allPersons() {
		return personRepository.getAllPersons();
	}
}
