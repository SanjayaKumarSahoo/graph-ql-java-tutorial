package PersonRepository;

import model.Person;

import java.util.ArrayList;
import java.util.List;

public class PersonsRepository {


    private static final List<Person> personList = new ArrayList<>();


    public List<Person> allPersons() {
        return personList;
    }

    public void savePerson(Person person) {
        personList.add(person);
    }

}
