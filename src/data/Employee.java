package data;

import java.util.Date;

public class Employee {
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private double experience;

    // Getters and Setters
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public Date getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(Date dateOfBirth) { this.dateOfBirth = dateOfBirth; }
    public double getExperience() { return experience; }
    public void setExperience(double experience) { this.experience = experience; }
}

