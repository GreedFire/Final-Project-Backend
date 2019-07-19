package com.kodilla.backend.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;




@NamedNativeQueries({
        @NamedNativeQuery(
                name = "User.signIn",
                query = "UPDATE USERS SET SIGNED_IN = 'TRUE'  WHERE ID = :ID",
                resultClass = User.class
        ),

        @NamedNativeQuery(
                name = "User.signOut",
                query = "UPDATE USERS SET SIGNED_IN = 'FALSE'  WHERE ID = :ID",
                resultClass = User.class
        ),

})

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String username;

    private String password;

    private String email;

    private String firstname;

    private String lastname;

    private LocalDate birthdate;

    private boolean signedIn;

    public User(String username, String password, String email, String firstname, String lastname, LocalDate birthdate, boolean signedIn) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthdate = birthdate;
        this.signedIn = signedIn;
    }
}
