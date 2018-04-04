package mutation;

import PersonRepository.PersonsInMemoryRepository;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import model.Person;
import model.Post;

import java.util.List;
import java.util.UUID;

public class Mutation implements GraphQLMutationResolver {

    private PersonsInMemoryRepository personsInMemoryRepository;

    public Mutation(PersonsInMemoryRepository personsInMemoryRepository) {
        this.personsInMemoryRepository = personsInMemoryRepository;
    }

    public Person savePerson(String name, int age, List<Post> posts) {
        Person person = new Person();
        person.setId(UUID.randomUUID().toString());
        person.setName(name);
        person.setAge(age);
        posts.forEach(post -> post.setId(UUID.randomUUID().toString()));
        person.setPosts(posts);
        return personsInMemoryRepository.savePerson(person);
    }
}
