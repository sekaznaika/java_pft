package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class ContactData {
    private final String firstname;
    private int id;
    //private final String middlename;
    private final String lastname;
    private final String address;
    private final String email;
    private String group;

    public ContactData(int id, String firstname, String lastname, String address, String email, String group) {
        this.id = id;
        this.firstname = firstname;
        //this.middlename = middlename;
        this.lastname = lastname;
        this.address = address;
        this.email = email;
        this.group = group;
    }

    public ContactData(String firstname, String lastname, String address, String email, String group) {
        this.id = Integer.MAX_VALUE;
        this.firstname = firstname;
        //this.middlename = middlename;
        this.lastname = lastname;
        this.address = address;
        this.email = email;
        this.group = group;
    }

    public String getFirstname() {
        return firstname;
    }

    /*public String getMiddlename() {
        return middlename;
    }*/

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastname() {
        return lastname;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getGroup() {
        return group;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return Objects.equals(firstname, that.firstname) && Objects.equals(lastname, that.lastname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstname, lastname);
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }


}
