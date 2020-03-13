package org.fasttrackit.onlineshop.transfer.customer;

import javax.validation.constraints.NotNull;

public class SaveCurstomerRequest {
    @NotNull
    private String firstName;

    @Override
    public String toString() {
        return "SaveCurstomerRequest{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @NotNull
    private String lastName;
}
