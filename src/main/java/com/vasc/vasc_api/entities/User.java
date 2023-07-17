package com.vasc.vasc_api.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "user")
public class User {

public User(){

}

public User(String firstName, String lastName, String email, String password ){

    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.password = password;
}

@Id
@GeneratedValue(strategy= GenerationType.IDENTITY)
@Column(name = "id", nullable= false, updatable = false)
    private Integer id;



@Column(name = "first_name")
    private String firstName;

@Column(name = "last_name")
    private String lastName;

@Column(name = "email")
    private String email;

@Column(name = "password")
    private String password;



    // ***************************** G E T T E R S _ _ A N D _ _ S E T T E R S*************************************

public Integer getId(){
    return this.id;
}

//public void setId(Integer id){
//    this.id = id;
//}


public String getFirstName(){
    return this.firstName;
}

public void setFirstName(String firstName){
    this.firstName = firstName;
}

public String getLastName(){
    return this.lastName;
}

public void setLastName(String lastName){
    this.lastName = lastName;
}

public String getEmail(){
    return this.email;
}

public void setEmail(String email){
    this.email = email;
}

public String getPassword(){
    return this.password;
}

public void setPassword(String password){
    this.password = password;
}



}
