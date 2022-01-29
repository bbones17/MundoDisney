package com.disney.mundo.Model.Entities.Security;

import com.disney.mundo.Model.Entities.Security.User;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "confirmation_token")
public class ConfirmationToken {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="token_id")
    Integer tokenid;

    @Column(name="confirmation_token")
     String confirmationToken;

    @Temporal(TemporalType.TIMESTAMP)
    Date createdDate;



    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
     User user;



    public ConfirmationToken(User user) {
        this.user = user;
        createdDate = new Date();
        confirmationToken = UUID.randomUUID().toString();
    }


    public Integer getTokenid() {
        return tokenid;
    }

    public String getConfirmationToken() {
        return confirmationToken;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public User getUser() {
        return user;
    }

    public ConfirmationToken(){}
}