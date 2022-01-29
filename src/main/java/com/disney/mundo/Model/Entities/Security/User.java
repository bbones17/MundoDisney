package com.disney.mundo.Model.Entities.Security;

import lombok.Data;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.crypto.bcrypt.BCrypt;

import javax.persistence.*;
import java.util.Set;


@Getter
@Setter
@ToString
@Entity
@Table(name = "users")
public class User {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="userid")
    Integer userid;
    @Column(name="emailid")
    String emailId;
    @Column(name="password")
    String password;
    @Column(name="isenabled")
    Boolean isEnabled;


    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "userid", referencedColumnName = "userid"),
            inverseJoinColumns = @JoinColumn(name = "roleid", referencedColumnName = "roleid"))
    private Set<Role> roles;


    public void setEnabled(Boolean value) {
        this.isEnabled=value;
    }
    public User(String emailId,String password)
    {
        this.userid=null;
        this.emailId=emailId;
        this.password= password;
        this.isEnabled=false;
    }
    public User()
    {
    }
}