package com.christianpari;

public class Person {
  // VARIABLES
  private String lastName;
  private String firstName;

  // CONSTRUCTORS
  public Person(
    String firstName
    , String lastName
  ) {
    this.firstName = firstName;
    this.lastName = lastName;
  }

  // METHODS
    // OVERRIDES
  @Override
  public String toString() {
    return "firstName: " + firstName + ", lastName: " + lastName;
  }

    // GETTERS / SETTERS
  public String getFirstName() { return firstName; }
  public String getLastName() { return lastName; }
  public void setFirstName(String newFirstName) { firstName = newFirstName; }
  public void setLastName(String newLastName) { lastName = newLastName; }
}
