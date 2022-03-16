package model;

import java.util.regex.Pattern;

/**
 * This is Customer class, customer name and email are stored.
 * @author Ratan Dheeraj Kadirikota
 */
public class Customer {
    String firstName;
    String lastName;
    String email;
    public Customer(String email, String firstName, String lastName){
        String emailRegex = "^(.+)@(.+).(.+)$";
        Pattern pattern = Pattern.compile(emailRegex);
        if(pattern.matcher(email).matches()){
            this.email = email;
        }
        else{
            throw new IllegalArgumentException();
        }
        this.firstName = firstName;
        this.lastName = lastName;

    }
    public String getEmail() {
        return email;
    }
    @Override
    public String toString(){
        return "Customer Details:\nFirst name: "+firstName+"\nLast name: "+lastName+"\nEmail: "+email+"\n";
    }
}
