package com.kodewerk.cheapdrink;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class PersonStorage implements PersonStore {

    private final Map<String,Person> personsByName = Collections.synchronizedMap(new HashMap<String,Person>());
    private final Map<Integer,Person> personsById = Collections.synchronizedMap(new HashMap<Integer,Person>());

    public synchronized Person addPerson(String firstName, String lastName) {
        Person person = new Person(firstName, lastName);
        personsByName.put(firstName + lastName, person);
        personsById.put(person.getId(), person);
        return person;
    }

    public synchronized Person findPersonById(int id) {
        return personsById.get(id);
    }

    public synchronized Person findPersonByName(String firstName, String lastName) {
        return personsByName.get( firstName + lastName);
    }

}
