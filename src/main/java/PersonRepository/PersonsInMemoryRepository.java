package PersonRepository;

import model.Person;

import java.util.ArrayList;
import java.util.List;

public class PersonsInMemoryRepository {

    private static final List<Person> personList = new ArrayList<>();

    public List<Person> allPersons() {
        return personList;
    }

    public Person savePerson(Person person) {
        personList.add(person);
        return person;
    }

}
