package com.vasc.vasc_api.entities;


import jakarta.persistence.*;

@Entity
@Table (name = "feedback" )
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false,updatable = false)
    private Integer id;
    //@Column(nullable = false)
    private String FirstName;
    private String LastName;
    private String EmailId;
    private String FeedBack;

    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public String getEmailId() {
        return EmailId;
    }

    public String getFeedBack() {
        return FeedBack;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public void setEmailId(String emailId) {
        EmailId = emailId;
    }

    public void setFeedBack(String feedBack) {
        FeedBack = feedBack;
    }

    public Feedback(){

    }
    public Feedback(Integer id, String firstName, String lastName, String emailId, String feedBack) {
        this.id = id;
        FirstName = firstName;
        LastName = lastName;
        EmailId = emailId;
        FeedBack = feedBack;
    }
}
