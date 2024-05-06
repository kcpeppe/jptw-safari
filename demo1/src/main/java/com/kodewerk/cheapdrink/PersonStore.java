package com.kodewerk.cheapdrink;

public interface PersonStore {

    Person addPerson(String firstName, String lastName);
    Person findPersonByName(String firstName, String lastName);

}
