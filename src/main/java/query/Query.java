package query;

import PersonRepository.PersonsInMemoryRepository;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import model.Person;

import java.util.List;

public class Query implements GraphQLQueryResolver {

    private final PersonsInMemoryRepository personsRepository;

    public Query(PersonsInMemoryRepository personsRepository) {
        this.personsRepository = personsRepository;
    }

    public List<Person> allPersons() {
        return personsRepository.allPersons();
    }

}
