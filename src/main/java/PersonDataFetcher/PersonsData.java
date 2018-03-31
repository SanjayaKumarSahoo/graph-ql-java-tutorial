package PersonDataFetcher;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import model.Person;
import model.Post;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class PersonsData {

    private static List<Person> personList = new ArrayList<>();


    public static DataFetcher<List<Person>> getAllPersons() {
        return (environment) -> getAllPersonsData();
    }

    public static DataFetcher<Person> savePersons() {
        return (environment) -> savePersonData(environment);
    }

    private static Person savePersonData(DataFetchingEnvironment environment) {
        Person person = new Person();
        person.setId(UUID.randomUUID().toString());
        person.setAge(environment.getArgument("age"));
        person.setName(environment.getArgument("name"));
        personList.add(person);
        return person;
    }


    private static List<Person> getAllPersonsData() {

        Person person = new Person();
        person.setId(UUID.randomUUID().toString());
        person.setAge(45);
        person.setName("Allen");

        Post post1 = new Post();
        post1.setTitle("How to influence people");
        post1.setId(UUID.randomUUID().toString());

        Post post2 = new Post();
        post2.setTitle("Rich & Poor");
        post2.setId(UUID.randomUUID().toString());

        person.setPosts(Arrays.asList(post1, post2));


        Person person2 = new Person();
        person2.setId(UUID.randomUUID().toString());
        person2.setAge(46);
        person2.setName("Bob");


        personList.add(person);
        personList.add(person2);
        return personList;
    }

}
