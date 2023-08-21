package org.example;

public class Person {
    private String firstName;
    private String lastName;
    private int age;
    private Person partner;

    public Person(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public boolean isRetired() {
        // Define your retirement logic here based on age or other criteria
        return false;
    }

    public String getLastName() {
        return lastName;
    }

    public void setPartner(Person partner) {
        this.partner = partner;
    }

    public Person getPartner() {
        return partner;
    }

    public void registerPartnership(Person partner) {
        setPartner(partner);
        partner.setPartner(this);
        // Implement any logic for registering partnership
    }

    public void deregisterPartnership(boolean restoreLastName) {
        if (partner != null) {
            partner.setPartner(null);
            if (restoreLastName) {
                lastName = partner.getLastName();
            }
            partner = null;

        }
    }

    public int getAge() {
        return age;
    }
}
