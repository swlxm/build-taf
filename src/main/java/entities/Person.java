package entities;

import lombok.Builder;

@Builder
public class Person {

    private String firstName;

    private String lastName;

    private int age;

    private String address;

    private String email;

//    public Person(String firstName, String lastName) {
//        this.firstName = firstName;
//        this.lastName = lastName;
//    }
//
//    public Person(String firstName, String lastName, int age) {
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.age = age;
//    }
}
