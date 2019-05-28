/*     Team: Wasted Potential
    Project: ProDeo Router
       File: Login.java
Description: Takes mulitple addresses, and finds quickest route */
package gui;

public class Login {
    private String firstName = null;
    private String lastName = null;
    private String timeStamp = null;
    private String wasSuccessful = null;

    public Login() {
    }

    public Login(String firstName, String lastName, String loginTime, String wasSuccessful) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.timeStamp = loginTime;
        this.wasSuccessful = wasSuccessful;
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
    
    public String getTimeStamp() {
        return timeStamp;
    }
    
    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }
    
    public String getSuccessful() {
        return wasSuccessful;
    }
    
    public void setSuccessful(String wasSuccessful) {
        this.wasSuccessful = wasSuccessful;
    }
}