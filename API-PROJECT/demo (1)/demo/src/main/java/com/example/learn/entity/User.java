// package com.example.learn.entity;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.*;
// @Entity
// public class User {
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY) 
//     int id;
//     String name;
//     String email;
//     @Transient
//     String password;
//     String preferredlanguage;
//     String profilePicture;
//     public User()
//     {

//     }
//     public User(int id, String name, String email, String password, String preferredlanguage, String profilePicture) 
//     {
//         this.id = id;
//         this.name = name;
//         this.email = email;
//         this.password = password;
//         this.preferredlanguage = preferredlanguage;
//         this.profilePicture = profilePicture;
//     }
//     public int getId() 
//     {
//         return id;
//     }
//     public void setId(int id) 
//     {
//         this.id = id;
//     }
//     public String getName()
//     {
//         return name;
//     }
//     public void setName(String name) 
//     {
//         this.name = name;
//     }
//     public String getEmail() 
//     {
//         return email;
//     }
//     public void setEmail(String email) 
//     {
//         this.email = email;
//     }
//     public String getPassword() 
//     {
//         return password;
//     }
//     public void setPassword(String password) 
//     {
//         this.password = password;
//     }
//     public String getPreferredlanguage() 
//     {
//         return preferredlanguage;
//     }
//     public void setPreferredlanguage(String preferredlanguage) 
//     {
//         this.preferredlanguage = preferredlanguage;
//     }
//     public String getProfilePicture() 
//     {
//         return profilePicture;
//     }
//     public void setProfilePicture(String profilePicture) 
//     {
//         this.profilePicture = profilePicture;
//     }
// }
package com.example.learn.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String email;

    @Transient
    private String password;

    private String preferredlanguage;
    private String profilePicture;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Enrollment> enrollments;

    // Constructors
    public User() {}

    public User(int id, String name, String email, String password, String preferredlanguage, String profilePicture) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.preferredlanguage = preferredlanguage;
        this.profilePicture = profilePicture;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPreferredlanguage() {
        return preferredlanguage;
    }

    public void setPreferredlanguage(String preferredlanguage) {
        this.preferredlanguage = preferredlanguage;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public List<Enrollment> getEnrollments() {
        return enrollments;
    }

    public void setEnrollments(List<Enrollment> enrollments) {
        this.enrollments = enrollments;
    }
}
