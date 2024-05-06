package com.kodewerk. cheapdrink;

public class Person {

    private static int count = 0;

    private int id;
    private final String firstName;
    private final String lastName;

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        id = count++;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }

}
